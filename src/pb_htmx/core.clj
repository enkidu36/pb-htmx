(ns pb-htmx.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as ring]
            [reitit.ring.middleware.parameters :refer [parameters-middleware]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.util.response :as response]
            [hiccup2.core :as h])
  (:gen-class))

(defonce counter-state (atom 0))

(defn layout [title content]
  (str
   (h/html
    [:html
     [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:name "viewport", :content "width=device-width, initial-scale=1"}]
      [:title title]
      [:script {:src "/public/htmx.min.js"} ""]
      [:link {:rel "stylesheet", :href ""}]]
     [:body {:style "font-family: sans-serif; padding: 2rem;"}
      [:img {:src "/public/img_la.jpg", :alt "Los Angeles", :style "width: 80%; border-radius: 90px;"}]
      [:main content]
      [:script {:src ""}]]])))

;; Handlers
(defn home-handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (layout "Clojure & htmx App" "Home Page")})

(def app-routes
  (ring/ring-handler
   (ring/router
    [""
     ["/" {:get home-handler}]
     ["/public/*" (ring/create-resource-handler {:root "public"})]]
    {:data {:middleware [parameters-middleware]}})
   (ring/create-default-handler)))

(def app (wrap-reload #'app-routes))

(defonce server (atom nil))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)
    (println "Server stopped.")))

(defn start-server []
  (stop-server)
  (reset! server (run-jetty #'app {:port 3000 :join? false}))
  (println "Starting server on port 3000..."))

(defn -main [& args]
  (start-server))


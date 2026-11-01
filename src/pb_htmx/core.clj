(ns pb-htmx.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as ring]
            [reitit.ring.middleware.parameters :refer [parameters-middleware]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.util.response :as response]
            [hiccup2.core :as h])
  (:gen-class))

(defonce counter-state (atom 0))

;; FIX 1: Wrap Hiccup output in raw-string container or cast explicitly 
;; so that it outputs a clean, well-formed HTML wrapper string.
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
   :body (layout "Clojure & htmx App" "Home Pageeee")})

;; FIX 2: Define your router inside an anonymous function or use a function wrapper.
;; This ensures Reitit rebuilds the routing table using updated Var references 
;; every single time a request comes in during development.
(defn app-routes [request]
  ((ring/ring-handler
    (ring/router
     [""
      ["/" {:get home-handler}]
      ;; FIX 3: Reitit looks inside your "resources" folder by default. 
      ;; Changing path to "public" matches /resources/public/*
      ["/public/*" (ring/create-resource-handler {:root "public"})]]
     {:data {:middleware [parameters-middleware]}})
    (ring/create-default-handler))
   request))

;; Use Var indirection (#'app-routes) inside wrap-reload so Clojure doesn't cache the old handler pointer
(def app (wrap-reload #'app-routes))

;; FIX 4: Track the running server instance in an atom so we don't accidentally leak background ports
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


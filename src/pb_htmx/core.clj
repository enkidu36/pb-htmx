(ns pb-htmx.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as ring]
            [reitit.ring.middleware.parameters :refer [parameters-middleware]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.util.response :as response]
            [hiccup2.core :as h])
  (:gen-class))

(defonce counter-state (atom 0))

(defn page-layout [title content]
  (str
   (h/html
    [:html
     [:head
      [:title title]
      [:script {:src "/public/htmx.min.js"} ""]]
     [:body {:style "font-family: sans-serif; padding: 2rem;"}
      content]])))


;; Handlers
(defn home-handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   ;; Dereference the atom using @ to read its current state
   :body (page-layout "Clojure & htmx App" "Home Page")})

(def app-routes
  (ring/ring-handler
   (ring/router
    [""
     ["/" {:get home-handler}]
     ["/public/*" (ring/create-resource-handler {:root "public"})]]
    {:data {:middleware [parameters-middleware]}})
   (ring/create-default-handler)))

(def app (wrap-reload #'app-routes))

(defn -main [& args]
  (println "Starting server on port 3000...")
  (run-jetty #'app {:port 3000 "join?" false}))

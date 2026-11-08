(ns pb-htmx.app
  (:require
   [reitit.ring :as ring]
   [reitit.ring.middleware.parameters :refer [parameters-middleware]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.util.response :as response]
   [pb-htmx.handlers :as h]))

(def app-routes
  (ring/ring-handler
   (ring/router
    [""
     ["/" {:get h/home-handler}]
     ["/public/*" (ring/create-resource-handler {:root "public"})]]
    {:data {:middleware [parameters-middleware]}})
   (ring/create-default-handler)))

(def app (wrap-reload #'app-routes))

(ns pb-htmx.app
  (:require
   [reitit.ring :as ring]
   [reitit.ring.middleware.parameters :refer [parameters-middleware]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.util.response :as response]
   [pb-htmx.response-utils :as r]
   [pb-htmx.layout :refer [layout]]
   [pb-htmx.views.home :refer [home]]
   [pb-htmx.views.calendar :refer [calendar]]
   [pb-htmx.views.dashboard :refer [dashboard]]
   [pb-htmx.views.atp :refer [atp]]
   [pb-htmx.index :refer [index]]
   [pb-htmx.views.sandbox :refer [sandbox]]))

(def views [{:name "Home" :path "/"}
            {:name "Calendar" :path "/calendar"}
            {:name "Dashboard" :path "/dashboard"}
            {:name "ATP" :path "/atp"}])

(defn wrap-layout [handler title]
  (fn [request]
    (when-let [response (handler request)]
      (r/ok-html (layout views title response)))))

(defn wrap-index [handler title]
  (fn [request]
    (when-let [response (handler request)]
      (r/ok-html (index title response)))))

(def app-routes
  (ring/ring-handler
   (ring/router
    [
     ["/" {:get home
           :middleware [[wrap-layout "Home"]]}]
     ["/sandbox" {:get sandbox
                  :middleware [[wrap-index "Sandbox"]]
                }]
     ["/calendar" {:get calendar
                   :middleware [[wrap-layout "Calendar"]]}]
     ["/dashboard" {:get dashboard
                    :middleware [[wrap-layout "Dashboard"]]}]
     ["/atp" {:get atp
              :middleware [[wrap-layout "ATP"]]}]
     ["/public/*" (ring/create-resource-handler {:root "public"})]]
    {:data {:middleware [parameters-middleware]}})
   (ring/create-default-handler)))

(def app (wrap-reload #'app-routes))

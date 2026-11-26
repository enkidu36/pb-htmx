(ns pb-htmx.app
  (:require
   [reitit.ring :as ring]
   [reitit.ring.middleware.parameters :refer [parameters-middleware]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.middleware.refresh :refer [wrap-refresh]]
   [pb-htmx.response-utils :as r]
   [pb-htmx.layout :refer [layout]]
   [pb-htmx.views.home :refer [home]]
   [pb-htmx.views.calendar :refer [calendar]]
   [pb-htmx.views.dashboard :refer [dashboard]]
   [pb-htmx.views.atp :refer [atp]]
   [pb-htmx.views.sandbox :refer [sandbox]]))

(defn wrap-layout [handler title]
  (fn [request]
    (when-let [response (handler request)]
      ;; Use #'layout so layout edits reload without restarting
      (r/ok-html (#'layout views title response)))))

;; Make app-routes a function (or use #'home, #'calendar, etc.)
;; so Reitit resolves the newly reloaded view vars on every request
(defn create-app []
  (ring/ring-handler
   (ring/router
    [["/" {:get #'home
           :middleware [[wrap-layout "Home"]]}]
     ["/sandbox" {:get #'sandbox
                  :middleware [[wrap-layout "Sandbox"]]}]
     ["/calendar" {:get #'calendar
                   :middleware [[wrap-layout "Calendar"]]}]
     ["/dashboard" {:get #'dashboard
                    :middleware [[wrap-layout "Dashboard"]]}]
     ["/atp" {:get #'atp
              :middleware [[wrap-layout "ATP"]]}]
     ["/public/*" (ring/create-file-handler {:root "resources/public"})]]
    {:data {:middleware [parameters-middleware]}})
   (ring/create-default-handler)))

(def app
  (-> (fn [req] ((create-app) req))
      (wrap-reload {:dirs ["src"]})
      ;; Watches src and resources for .clj/.css edits and triggers browser refresh
      (wrap-refresh ["src" "resources"])))

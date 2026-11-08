(ns pb-htmx.main
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [pb-htmx.app :refer [app]])
  (:gen-class))


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


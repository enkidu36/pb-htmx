(ns pb-htmx.handlers
  (:require
   [pb-htmx.layout :refer [layout]]
   [pb-htmx.response-utils :as r]))

(defn home-handler [request]
  (r/https-ok (layout "Clojure & htmx App" "Home Page")))

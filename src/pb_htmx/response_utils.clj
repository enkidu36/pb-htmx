(ns pb-htmx.response-utils
  (:require [ring.util.response :as response]))

(defn https-ok [content]
  (-> content
      (response/response)
      (response/header "Content-Type" "text/html")))

(ns pb-htmx.response-utils
  (:require [ring.util.response :as response]))

(defn ok [content]
  (-> content
      response/response))

(defn ok-html [content]
  (-> content
      (response/response)
      (response/header "Content-Type" "text/html; charset=utf-8")))

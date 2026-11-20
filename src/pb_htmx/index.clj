(ns pb-htmx.index
  (:require [hiccup2.core :as h]))



(defn index [title content]
  (str
   (h/html
    (h/raw "<!DOCTYPE html>")
    [:html
     [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:name "viewport", :content "width=device-width, initial-scale=1"}]
      [:title title]
      [:script {:src "/public/htmx.min.js"} ""]
;;      [:link {:rel "stylesheet", :href "/public/css/pico.min.css"}]
      [:link {:rel "stylesheet", :href "/public/css/sbx.css"}]]
      [:script {:src "/public/script.js" :defer "true"}]
     [:body
      content]])))



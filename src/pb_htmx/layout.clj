(ns pb-htmx.layout
  (:require [hiccup2.core :as h]))

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
      [:main content]
      [:script {:src ""}]]])))

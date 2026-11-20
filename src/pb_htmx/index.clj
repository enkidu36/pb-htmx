(ns pb-htmx.index
  (:require [hiccup2.core :as h]
            [hiccup.page :refer [html5 include-css include-js]]))

(defn index [title content]
   (html5
     [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:name "viewport", :content "width=device-width, initial-scale=1"}]
      [:title title]
      (include-css "/public/css/sbx.css")
      (include-js "/public/script.js")
      [:body
       content]]))



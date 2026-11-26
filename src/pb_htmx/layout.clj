(ns pb-htmx.layout
  (:require [hiccup2.core :as h]
            [pb-htmx.icons :as icons]))

(defn create-nav-li
  "Create navigation page links. 
   Checks the title and sets active class if matches link name. "
  [path name title]
  (let [list-item  (if (= name title) [:li.active] [:li])]
    (conj list-item [:a {:hx-get path :hx-target "body" :hx-replace-url "true"} name])))

(defn layout [views title content]
  (str
   (h/html
    (h/raw "<!DOCTYPE html>")
    [:html {:lang "en"}
     [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
      [:title title]
      [:link {:rel "preconnect" :href "https://fonts.googleapis.com"}]
      [:link {:rel "preconnect" :href "https://fonts.gstatic.com" :crossorigin ""}]
      [:link {:rel "stylesheet" :href "https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@400;500;600;700&family=Inter:wght@400;500;600;700&display=swap"}]
      [:link {:rel "stylesheet" :href "/public/css/style.css"}]
      [:script {:src "/public/htmx.min.js"}]
      [:script {:src "/public/script.js" :defer "true"}]]
     [:body
      [:header.appHeader
       [:ul
        [:li icons/app-logo]
        (create-nav-li "/" "Home" title)
        (create-nav-li "/calendar" "Calendar" title)
        (create-nav-li "/dashboard" "Dashboard" title)
        (create-nav-li "/atp" "ATP" title)
        [:li icons/user]
        [:li "Joseph Savoie"]
        [:li icons/messages]
        [:li icons/bell]
        [:li icons/full-screen]]]
      content]])))

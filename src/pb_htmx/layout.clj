(ns pb-htmx.layout
  (:require [hiccup2.core :as h]))

(defn app-logo
  "App logo"
  []
  [:img {:src "/public/images/logo.svg"}])

(defn nav-menu
  "Creates menu links for views & marks the active page menu item"
  [title views]
  [:nav.navMenu
   (for [{:keys [name path]} views
         :let [item (if (= name title)
                      [:div.active]
                      [:div])]]
     (conj item {:hx-get path :hx-target "body" :hx-replace-url "true"} name))])

(defn user-menu
  "Menu links for Signed in User"
  [name]
  [:nav.userMenu
   [:img {:src "/public/images/user-solid-full.svg"}]
   name
   (for [img ["message-regular-full.svg" "up-right-and-down-left-from-center-solid-full.svg"]]
     [:img {:src (str "/public/images/" img)}])])

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
      [:link {:rel "stylesheet"
              :href "https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@400;500;600;700&family=Inter:wght@400;500;600;700&display=swap"}]
      [:link {:rel "stylesheet" :href "/public/css/styles.css"}]
      [:script {:src "/public/htmx.min.js"}]
      [:script {:src "/public/script.js" :defer "true"}]]
     [:body
      [:header.appHeader
       (app-logo)
       (nav-menu title views)
       (user-menu "Joseph Savoie")]
      content]])))

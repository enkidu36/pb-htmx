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
     (conj item  {:hx-get path :hx-target "body"} name))])

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
    [:html
     [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:name "viewport", :content "width=device-width, initial-scale=1"}]
      [:title title]
      [:script {:src "/public/htmx.min.js"} ""]
      [:link {:rel "stylesheet", :href "/public/css/styles.css"}]]
     [:body
      (list
       [:header.appHeader

        (app-logo)
        (nav-menu title views)
        (user-menu "Josep Savoie")])

      content]])))

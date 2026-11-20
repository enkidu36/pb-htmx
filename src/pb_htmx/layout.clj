(ns pb-htmx.layout
  (:require [hiccup2.core :as h]))

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
         ;;         [:link {:rel "stylesheet", :href "/public/css/pico.min.css"}]
         [:link {:rel "stylesheet", :href "/public/css/styles.css"}]]
        [:body.app
         [:div
          [:header.appHeader
           [:img {:src "/public/images/logo.svg" }]
           [:nav.navMenu
            (for [{:keys [name path]} views
                  :let [item (if (= name title) [:div.active] [:div])]]
              (conj item  {:hx-get path :hx-target "body"} name))]
           [:nav.altNavMenu
            [:div.user
             [:img {:src "/public/images/user-solid-full.svg"}]
             [:div "Joseph Savoie"]]
            [:div.icons 
             (for [img ["message-regular-full.svg" "up-right-and-down-left-from-center-solid-full.svg"]]
               [:img {:src (str "/public/images/" img)}])] ]]
          [:header.subHeader [:h2  "Joseph Savoie"]]]
          content]])))

(ns pb-htmx.views.sandbox
  (:require [hiccup2.core :as h]))

(defn navbar []
  (list
   
   [:button#open-sidebar-button {:onclick "openSidebar()"}
    [:img {:src "/public/images/menu.svg" :width "36px" :height "36px"}]]
   
   [:nav#navbar
    [:ul
     [:li [:button#close-sidebar-button {:onclick "closeSidebar()"} "X"]]
     [:li.home-li [:a.active-link {:href ""} "Homeee"]]
     [:li [:a {:href ""} "Calendar"]]
     [:li [:a {:href ""} "Dashboard"]]
     [:li [:a.accent-link {:href ""} "ATP"]]]]

   [:div#overlay {:onclick "closeSidebar()"}]))

(defn sandbox [_]
  (h/html
   [:header
    (navbar)]))

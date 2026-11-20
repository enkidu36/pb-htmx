(ns pb-htmx.views.home
  (:require
   [hiccup2.core :as h]))

(def training-links
   ["Boost your threshold"
    "Base Training"
    "Ride 100 Miles"
    "Virtual Training Racing"
    "Cyclocross"
    "Weight Training"]  )

(defn info-section []
  [:section
   [:div {:style {:margin "8px"}}
    [:h4 "Training Plans"]
    [:hr ]
    [:span "Beat your best with these plans."]
    [:div.button-container
     (for [link training-links]
       [:button.smallButtons  link])]]
   [:h4 "Events"]
   [:h4 "Goals"]])

(defn home [request]
  [:div#home.container
   (info-section)
   [:section "Today's Training"]
   [:section "Performance"]])



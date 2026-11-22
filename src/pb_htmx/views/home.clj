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
  [:section#home-info
   [:h3 "Training Plans"]
   [:hr]
   [:p.section-subtitle "Beat your best with these plans."]
   [:div.button-container
    (for [link training-links]
      [:button.smallButtons {:type "button"} link])]
   [:hr]
   [:h4 "Events"]
   [:hr]
   [:h4 "Goals"]])

(defn home [request]
  [:div#home.container
   [:header "Athlete: Joseph Savoie"]
   (info-section)
   [:section#todays-training
    [:h3 "Today's Training"]
    [:hr]]
   [:section#performance.performance-section
    [:h3 "Performance"]
    [:hr]]])

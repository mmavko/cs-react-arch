(ns cs-react-arch.components.a
  (:require [rum]
            [cs-react-arch.shared :refer [raiser]]))

(def raise! (raiser :a))

(rum/defc A [world]
  (let [{:keys [a-data]} world]
    [:div
     [:p
      "This is an " [:code "A"]
      " component showing " [:code ":a-data"] ":"]
     [:p
      [:code a-data] " "
      [:button {:on-click #(raise! :update-data)}
       "Update!"]]
     [:p
      [:button {:on-click #(raise! [:main :switch-screen] :b)}
       "Switch to B cmp"]]]))

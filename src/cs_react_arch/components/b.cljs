(ns cs-react-arch.components.b
  (:require [rum.core :as rum]
            [cs-react-arch.shared :refer [raiser]]))

(def raise! (raiser :b))

(rum/defc B [world]
  (let [{:keys [b-data]} world]
    [:div
     [:p
      "This is a " [:code "B"]
      " component showing " [:code ":b-data"] ":"]
     [:p
      [:code b-data] " "
      [:button {:on-click #(raise! :update-data)}
       "Update!"]]
     [:p
      [:button {:on-click #(raise! [:main :switch-screen] :a)}
       "Switch to A cmp"]]]))

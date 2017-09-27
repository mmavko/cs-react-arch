(ns cs-react-arch.components.main
  (:require [rum.core :as rum]
            [cs-react-arch.shared :refer [raiser]]
            [cs-react-arch.components.a :refer [A]]
            [cs-react-arch.components.b :refer [B]]))

(def raise! (raiser :main))

(rum/defc Main [world]
  (let [{:keys [screen]} world]
    [:div
     (case screen
       :a (A world)
       :b (B world)
       [:div "What?"])]))

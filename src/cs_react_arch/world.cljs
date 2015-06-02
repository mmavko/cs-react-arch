(ns cs-react-arch.world
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<!]]
            [cs-react-arch.shared :refer [message-chan]]
            [cs-react-arch.controller :refer [control]]))

(def initial-world
  {:mount-id 0
   :data-loaded false})

(defonce world-ref (atom initial-world))

(defonce start
  (go
    (while true
      (let [[msg & args] (<! message-chan)]
        (swap! world-ref #(control msg args %))))))

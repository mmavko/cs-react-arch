(ns cs-react-arch.controllers.a
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :as async :refer [<!]]
            [cs-react-arch.shared :refer [raiser]]))

(def raise! (raiser :a))

(defmulti control (fn [msg args world] msg))

(defmethod control :default [msg args world]
  (println "Warning! Unhandled message:" [:a msg])
  world)

(defmethod control :init [_ _ world]
  (assoc world :a-data 0))

(defmethod control :load [_ _ world] world)

(defmethod control :refresh [_ _ world] world)

(defmethod control :update-data [_ _ world]
  (update-in world [:a-data] inc))

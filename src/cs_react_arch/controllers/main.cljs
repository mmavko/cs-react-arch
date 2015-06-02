(ns cs-react-arch.controllers.main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :as async :refer [<!]]
            [cs-react-arch.shared :refer [raiser]]))

(def raise! (raiser :main))

(defmulti control (fn [msg args world] msg))

(defmethod control :default [msg args world]
  (println "Warning! Unhandled message:" [:main msg])
  world)

(defmethod control :init [_ _ world]
  (assoc world :screen :a))

(defmethod control :load [_ _ world] world)

(defmethod control :refresh [_ _ world] world)

(defmethod control :switch-screen [_ [screen] world]
  (assoc world :screen screen))

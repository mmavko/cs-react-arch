(ns ^:figwheel-always cs-react-arch.controller
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<! timeout]]
            [cs-react-arch.shared :refer [config-ref raise!]]
            [cs-react-arch.controllers.main :as main]
            [cs-react-arch.controllers.a :as a]
            [cs-react-arch.controllers.b :as b]))

(def controllers
  {:main main/control
   :a a/control
   :b b/control})

(defn- broadcast [msg args world]
  (reduce #(%2 msg args %1) world (vals controllers)))

(defmulti control
  (fn [msg args world]
    (if (vector? msg) ::redirect msg)))

(defmethod control :default [msg args world]
  (println "Warning! Unhandled message:" msg)
  world)

(defmethod control ::redirect [[c msg] args world]
  ((controllers c) msg args world))

(defmethod control :init [msg args world]
  (broadcast msg args world))

(defmethod control :mount [_ args world]
  (let [new-mount-id (-> world :mount-id inc)]
    (if-not (:data-loaded world)
      (raise! :load)
      (raise! :refresh))
    (raise! :schedule-refresh new-mount-id)
    (assoc world
      :data-loaded true
      :mount-id new-mount-id)))

(defmethod control :unmount [_ args world]
  (update-in world [:mount-id] inc))

(defmethod control :schedule-refresh [_ [mount-id] world]
  (let [refresh-timeout (:refresh @config-ref)]
    (when (pos? refresh-timeout)
      (go
        (<! (timeout refresh-timeout))
        (raise! :try-refresh mount-id))))
  world)

(defmethod control :try-refresh [_ [mount-id] world]
  (when (= mount-id (:mount-id world))
    (raise! :refresh)
    (raise! :schedule-refresh mount-id))
  world)

(defmethod control :load [msg args world]
  (broadcast msg args world))

(defmethod control :refresh [msg args world]
  (broadcast msg args world))

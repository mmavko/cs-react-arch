(ns cs-react-arch.shared
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [chan >!]]))

(def default-configuration
  {:refresh 60000})

(defonce config-ref (atom default-configuration))

(defn configure [config-params]
  (swap! config-ref into config-params))

(defonce message-chan (chan))

(defn raise! [& args]
  (go (>! message-chan args)))

(defn raiser [controller]
  (fn [msg & args]
    (let [message
          (if (vector? msg) msg
            [controller msg])]
      (apply raise! message args))))

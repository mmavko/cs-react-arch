(ns ^:figwheel-always cs-react-arch.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [chan <! >!]]
            [rum.core :as rum]
            [cs-react-arch.world :refer [world-ref]]
            [cs-react-arch.shared :as shared]
            [cs-react-arch.components.common :refer [Wrapper]]
            [cs-react-arch.components.main :refer [Main]]))

(enable-console-print!)

(defonce lifecycle-chan (chan))

(defn- lifecycle-raise! [& args]
  (go (>! lifecycle-chan args)))

(defn ^:export component []
  (Wrapper lifecycle-raise! #(Main @world-ref)))

(defn ^:export configure [config-source]
  (shared/configure (js->clj config-source :keywordize-keys true)))

(defn- lifecycle-loop []
  (go
    (while true
      (let [[msg react-cmp] (<! lifecycle-chan)]
        (case msg
          :will-mount
          (do
            (add-watch world-ref :force-update #(rum/request-render react-cmp))
            (shared/raise! :mount))
          :will-unmount
          (do
            (remove-watch world-ref :force-update)
            (shared/raise! :unmount))
          nil)))))

(defonce start
  (do
    (lifecycle-loop)
    (shared/raise! :init)))

;; following methods for testing and debugging

(defn ^:export reload []
  (shared/raise! :refresh))

(ns cs-react-arch.components.common
  (:require [rum.core :as rum]))

(rum/defc Wrapper <
  (let [notify
        (fn [message
             {[notify-fn] :rum/args
              component :rum/react-component
              :as state}]
          (notify-fn message component)
          state)]
    {:will-mount     (partial notify :will-mount)
     :did-mount      (partial notify :did-mount)
     :transfer-state (partial notify :will-receive-props)
     :will-update    (partial notify :will-update)
     :did-update     (partial notify :did-update)
     :will-unmount   (partial notify :will-unmount)})
  [notify-fn render-child]
  (render-child))

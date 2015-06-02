(ns cs-react-arch.dev
  (:require [cs-react-arch.core]
            [figwheel.client :as fw]))

(fw/start {
  :websocket-url "ws://localhost:3449/figwheel-ws"
  :on-jsload #(.renderApp js/window)})

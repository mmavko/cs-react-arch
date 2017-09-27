(defproject cs-react-arch "0.1.0-SNAPSHOT"
  :description "ClojureScript React app architecture"
  :url "https://github.com/mmavko/cs-react-arch"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/clojurescript "1.9.908"]
                 [figwheel "0.5.13"]
                 [org.clojure/core.async "0.3.443"]
                 [rum "0.10.8"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.13" :exclusions [org.clojure/clojure
                                                org.codehaus.plexus/plexus-utils
                                                commons-codec]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src" "dev_src"]
              :compiler {:output-to "resources/public/js/compiled/cs-react-arch.js"
                         :output-dir "resources/public/js/compiled/out"
                         :optimizations :none
                         :main cs-react-arch.dev
                         :asset-path "js/compiled/out"
                         :source-map true
                         :source-map-timestamp true
                         :cache-analysis true
                         :warnings {:single-segment-namespace false}}}
             {:id "min"
              :source-paths ["src"]
              :compiler {:output-to "resources/public/js/compiled/cs-react-arch.js"
                         :main cs-react-arch.core
                         :optimizations :advanced
                         :pretty-print false
                         :warnings {:single-segment-namespace false}}}]}

  :figwheel {:http-server-root "public"
             :server-port 3449})

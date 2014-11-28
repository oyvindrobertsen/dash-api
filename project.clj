(defproject dash-api "0.1.0-SNAPSHOT"
  :description "API for a personal dashboard type thingy."
  :url "http://github.com/oyvindrobertsen/dash-api"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.2.0"]
                 [ring/ring-defaults "0.1.2"]
                 [ring/ring-json "0.3.1"]
                 [cheshire "5.3.1"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler dash-api.core.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})

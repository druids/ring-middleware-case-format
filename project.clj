(defproject ring-middleware-case-format "0.2.0"
  :description "A Ring's middleware that converts a request into X case and a response into a Y case"
  :url "https://github.com/druids/ring-middleware-case-format"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}

  :dependencies [[camel-snake-kebab "0.4.0"]]

  :profiles {:dev {:plugins [[lein-cloverage "1.0.10"]
                             [lein-kibit "0.1.6"]
                             [jonase/eastwood "0.2.6"]]
                   :dependencies [[org.clojure/clojure "1.9.0"]
                                  [ring/ring-core "1.6.3"]]}})

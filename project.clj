(defproject cidekiq "0.1.0-SNAPSHOT"
  :description "A SideKiq client to queue/scedule job to sidekiq direcly"
  :url "https://noty.im"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :aot :all
  :main space.yeo.cidekiq
  :dependencies [[org.clojure/clojure "1.8.0"],
                 [org.clojure/data.json "0.2.6"],
                 [com.taoensso/carmine "2.16.0"]])

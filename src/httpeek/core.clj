(ns httpeek.core
  (:gen-class)
  (:require [clj-http.client :as client]))

(comment http://windstreamstinks.com ist nicht erreichbar)

(client/get "http://localhost:8090" 
             {:redirect-strategy :none :socket-timeout 10000 :connection-timeout 10000 :async? true :insecure? true}
             (fn [response] (println "response is:" response))
             (fn [exception] (println "Moo")))
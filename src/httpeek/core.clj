(ns httpeek.core
  (:gen-class)
  (:require [clj-http.client :as client]))

(println "\n###########################################################################")


(def n 20)

(def timeout 10000)

(def acm (clj-http.conn-mgr/make-reusable-async-conn-manager
          {:threads n
           :default-per-route n}))

(comment (client/get "https://exampleeeeee.com/" {:async? true} (fn [response] (println "response is:" (:status response))) (fn [exc] (println "exception is:" (:cause exc)))))


(comment (with-open [r (clojure.java.io/reader "C:\\code\\httpeek\\src\\test.txt")]
           (doseq [line (line-seq r)]
             (client/get (str "http://" line)
                         {:async? true
                          :connection-manager acm
                          :insecure? true
                          :redirect-strategy :none
                          :socket-timeout timeout
                          :connection-timeout timeout
                          :throw-exceptions false
                          :cookie-policy :none}
                         (fn [r] (println "response" line (:status r)))
                         (fn [e] (println "exception" line (:status e)))))))



;;;;;; Source: https://github.com/clojure-cookbook/clojure-cookbook/blob/master/04_local-io/4-09_read-write-files.asciidoc
;; Read File
(comment (with-open [r (clojure.java.io/reader "C:\\code\\httpeek\\src\\test.txt")]
           (doseq [line (line-seq r)]
             (println line))))

;; Write File
(comment (with-open [w (clojure.java.io/writer "stuff.txt")]
           (doseq [line some-large-seq-of-strings]
             (.write w line)
             (.newLine w))))

(comment (defn spitn
           "Append to file with newline"
           [path text]
           (spit path (str text "\n") :append true)))
(ns space.yeo.cidekiq
  (:gen-class
        :name space.yeo.cidekiq
            :methods [#^{:static true} [enqueue [String String "[Ljava.lang.Object;"] Long]
                      #^{:static true} [createQueue [String] Long]
                      #^{:static true} [schedule [float String String "[Ljava.lang.Object;"] Long]
                      ])

  (:require [taoensso.carmine :as car :refer (wcar)]
            [clojure.data.json :as json])
  (:import java.security.SecureRandom
                      [org.apache.commons.codec.binary Hex]))

(def redis-uri (or (System/getenv "REDIS_URI") "redis://127.0.0.1:6379/"))
(def red-conn {:pool {} :spec {:uri redis-uri}})
(defmacro wcar* [& body] `(car/wcar red-conn  ~@body))

(defn ping
  "Ping Redis"
  []
  (println (wcar* (car/ping))))

(defn secure-bytes
  "Returns a random byte array of the specified size."
  [size]
  (let [seed (byte-array size)]
        (.nextBytes (SecureRandom.) seed)
            seed))

(defn- gen-job-id
  [size]
  (String. (Hex/encodeHex (secure-bytes size))))

(defn- gen-enqueue-at
  []
  (float (/ (System/currentTimeMillis) 1000)))

(defn create-queue
  "Create a sidekiq queue, which is a redis list and add queu name to set"
  [q]
  (wcar* (car/sadd "queues" q)))

(defn -createQueue [q] (create-queue q))

; https://github.com/mperham/sidekiq/blob/acdd35fb017bef7df97d514f0aed9b5b0b625678/lib/sidekiq/client.rb#L94-L98
(defn enqueue
  "Enqueue job from Mourgos to Sidekiq direcl"
  [q c args ]
  (let [on-queue (str "queue:" q)
        t (gen-enqueue-at)
        job {:class c :args args :retry 5 :queue q :jid (gen-job-id 12) :created_at t :enqueued_at t}]
    (wcar* (car/lpush on-queue (json/write-str job)))
    ))

(defn -enqueue
  [q c args ]
  (enqueue q c args))

(defn schedule
  "Schedule a job"
  [at q c args]
  (let [t (gen-enqueue-at)
        job {:class c :args args :retry 5 :queue q :jid (gen-job-id 12) :created_at t}]
    (wcar* (car/zadd "schedule" at (json/write-str job)))
    ))

(defn -schedule [at q c args] (schedule at q c args))

(defn -main
  []
  (println "Usage: This is a lib only. Please include jar file to call method"))

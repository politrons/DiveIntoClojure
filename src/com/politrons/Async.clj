(ns com.politrons.Async
  (:require [clojure.core.async
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! take! put! timeout]]
            [clojure.string :as str])
  (:gen-class))

;Future
;-------
; We [Future] in Clojure in order to run computation in another thread.
; We only need to use future function passing body, which it will be executed in another thread.
(def my-future (future
                 (println "Running computation on Thread:" + (.getName (Thread/currentThread)))
                 "Hello world in another thread"))

; With [deref] it block the future until the computation of the future it ends, and then get the return value.
(println (deref my-future))


;Future composition
;--------------------
; We can make future compose each other by just use [deref] of one future value inside another future
; Then future F[A] -> A to F[B] -> B
(def future-one (future "hello world"))

(future (
         (let [value (deref future-one)]
           (println "Future composition:" (str/upper-case value)))
         ))

; Async channel
;----------------
; [chan] Just like in Golang, allow you set some values in a channel where it can be shared between threads.
(def my-channel (chan))

(put! my-channel "Value in stored in another thread local")

(take! my-channel println)

; Channel to share info between threads.
(def shared-channel (chan))

(def my-future-publisher (future (put! shared-channel "Info shared between threads")))

(def my-future-consumer (future (take! shared-channel println)))

(println "Publisher: Data sent" (deref my-future-publisher))
(deref my-future-consumer)


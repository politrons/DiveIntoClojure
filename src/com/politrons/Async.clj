(ns com.politrons.Async
  (:require [clojure.core.async
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! take! put! timeout]]
            [clojure.string :as str])
  (:import [java.util.concurrent ForkJoinPool
                                 ForkJoinWorkerThread ForkJoinTask RecursiveTask])
  (:gen-class))

; Future
;--------
; We [Future] in Clojure in order to run computation in another thread.
; We only need to use future function passing body, which it will be executed in another thread.
(def my-future (future
                 (println "Running computation on Thread:" + (.getName (Thread/currentThread)))
                 "Hello world in another thread"))

; With [deref] it block the future until the computation of the future it ends, and then get the return value.
(println (deref my-future))

; Future transformation
;-----------------------
; Using [pmap] function, we can process each array element in another thread. We pass a function and then the array
; of elements
(def future-transformation (pmap (fn [word]
                                   (println "Transforming data in thread:" + (.getName (Thread/currentThread)))
                                   (str/upper-case word)
                                   ) ["hello", "future", "world"]))

(println "Future transformation:" future-transformation)

; Using [pcalls] function, we can pass an array of functions that they will be executed in another thread.
(def future-do-action (pcalls (fn []
                                (println "Async action1 " + (.getName (Thread/currentThread)))
                                "done"
                                )
                              (fn []
                                (println "Async action2 " + (.getName (Thread/currentThread)))
                                "done"
                                )
                              ))

(println "Future async actions:" future-do-action)

; Using [pvalues] function, we can pass an array of functions that they will set lazy into an array, and
; each function it will run in parallel.
(def future-async-array (pvalues (fn [] "Async value1")
                                 (fn [] "Async value2")
                                 ))

(doseq [item future-async-array]
  (println (item)))

; Future composition
;--------------------
; We can make future compose each other by just use [deref] of one future value inside another future
; Then future F[A] -> A to F[B] -> B
(def future-one (future (Thread/sleep 2000)
                        "hello world"))

(future (
         (let [value (deref future-one)]
           (println "Future composition:" (str/upper-case value)))
         ))

; Promise
;----------------
;; Using [promise] function we create a promise to be used for a future computation.
(def my-promise (promise))

;Start working on the promise in a new thread
;then deliver on your promise using [delivery] function
(future
  ;Computation
  (Thread/sleep 1000)
  (deliver my-promise "Hello promise world")
  )

; As we saw before, using [deref] it will block if the promise did not finish yet, and once it does
; it will extract the value
(println "Promise response:" (deref my-promise))

; Async channel
;----------------
; [chan] Just like in Golang, allow you set some values in a channel where it can be shared between threads.
(def my-channel (chan))

(put! my-channel "Value in stored in another thread local")

(take! my-channel println)

; Channel to share info between threads.
(def shared-channel (chan))

(def my-future-publisher (future (put! shared-channel "Info shared between threads")))

(future (take! shared-channel println))

(println "Publisher: Data sent" (deref my-future-publisher))


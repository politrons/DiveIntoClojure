(ns com.politrons.Async
  (:require [clojure.core.async
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! take! put! timeout]])
  (:gen-class))

(def simplechan (chan))

(put! simplechan "1")

(take! simplechan println)

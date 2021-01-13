(ns com.politrons.Functions)

; Functions
; ------------

; With Clojure the structure of the functions are [defn] [name] [param]
; the return value is consider the last expression of the body
(defn first-function [myParam]
  (println (clojure.string/upper-case myParam))
  (str (* 10 10))
  )

(println "return value:" (first-function "hello world"))

; In Clojure in order to receive n elements you must use [& elements]
; something similar as in Java with [...] and must be the last argument of the function
(defn list-features [& elements]
  (def myList (list elements))
  (map myList))

(println "return list:" (list-features 1, 2, 3, 4, 5))

(ns com.politrons.Functions
  (:require [clojure.string :as str]))

; [Defined Functions]
; ------------------
; With Clojure the structure of the [de]fined functions are [defn] [name] [params][body]
; the return value is consider the last expression of the body
(defn first-function [myParam]
  (println (clojure.string/upper-case myParam))
  (str (* 10 10))
  )

(println "return value:" (first-function "hello world"))

;; [Anonymous function/Lambdas]
;  -----------------------------
; is what lambdas(->) are in Java or Scala. Here the syntax is [fn] then the arguments inside [a,b] and the
; the body of the function (body)
(println ((fn [message] (clojure.string/upper-case message)) "Hello functional world"))

; [Higher order function]
; -------------------------
; Like in all functional language higher order functions are functions that receive or return a function
; In this example append [appendCharacterFunc] return a function that append two strings
(defn appendCharacterFunc [] (fn [message, character] (str message character)))

(println ((appendCharacterFunc) "Politrons" "!!"))

(defn apply-function-with-name [name-in-upper-case-function] (name-in-upper-case-function "politrons"))

(println (apply-function-with-name (fn [name] (str/upper-case name))))

; With Clojure we can have predicate functions like [filter] where we have to pass the anonymous function/lambda
; and then the collection in this example
(def result (filter (fn [word]
                      (or (= word "politrons") (= word "functional")))
                    ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]))


(println result)



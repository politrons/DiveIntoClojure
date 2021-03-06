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

;And in this example, we receive a function as argument and we invoke the function inside the body of the
; other function
(defn apply-function-with-name [name-in-upper-case-function] (name-in-upper-case-function "politrons"))

(println (apply-function-with-name (fn [name] (str/upper-case name))))

; [Filters]
; -----------
; With Clojure we can have predicate functions like [filter] where we have to pass the anonymous function/lambda
; and then the collection in this example
(defn filter-function [words] (filter (fn [word]
                      (or (= word "politrons") (= word "functional")))
                                      words))
(println (filter-function ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]))

; [Take] operator just take number of the elements of the array we want to extract from the array, to create a new one.
(def take-result (take 2 ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]))
(println take-result)

; [Drop] operator the antagonist of take, remove number of the elements of the array and create a new one
; with the rest of the elements that remains.
(def drop-result (drop 4 ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]))
(println drop-result)

; [take-while] function get a predicate function as argument to filter the number of elements to combine the new array until
; the predicate functions happens
(def take-while-result (take-while (fn [word] (> (count word) 4)) ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]))
(println take-while-result)

; [Transformation]
; -----------------
; [map] function get a functions that pass each element of the array and transform into a new element [A => B]
(def map-result (map (fn [word] (str/upper-case word)) ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]))
(println map-result)

; [mapcat] Behave just like [flatMap] in other functional languages like Scala, it meant to be used for composition,
; where in each iteration of the list, we can compose with another collection, and then flat all output elements together.
(def flat-map-result (mapcat (fn [word] (conj ["-"] word)) ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]))
(println flat-map-result)

; [Merge] operator get collections like map and merge all elements in just one map.
; in case one of the keys previously exist it will remain the one in the right definition.
(def merge-result (merge {:1 "hello"} {:2 "functional"} {:3 "world"} {:2 "clojure"}))
(println merge-result)

; [zipmap] is a handy operator to get two arrays and set each elements of the arrays as key-values
(def zipmap-result (zipmap [1 2 3 4] ["hello" "functional" "world" "again"]))
(println zipmap-result)
(println (zipmap-result 1))

; [Comparators]
; -----------------
; [comparator] function receive a predicate function, and return a comparator function to apply
; passing two arguments, and return 1, 0, -1 if is lower, equal or higher
(def comparator-func (comparator (fn [a b] (> a b))))
(println comparator-func)
(println (comparator-func 10 20))
(println (comparator-func 20 20))
(println (comparator-func 30 20))

; [sort] function just get an array and sort it
(def sort-result (sort [40, 13, 1, 5, 3, 8, 6]))
(println sort-result)

; [Reduce/Fold]
; -----------
; [Reduce] operator receive a function where it receive two arguments the [accumulator] and the [next] iteration
; value of the collection. Finally it return the [accumulator]
(def reduce-result (reduce
                     (fn [acc next]
                       (println "Acc:" acc)
                       (println "Next:" next)
                       (+ acc next)
                       )
                     [1 2 3 4]))
(println "Reduce:" reduce-result)

; In case you pass a value as second argument, it will be the initial value to start the [accumulator]
(def reduce-val-result (reduce (fn [acc next] (+ acc next)) 100 [1 2 3 4]))
(println "Reduce-val:" reduce-val-result)

; [Interval]
; -----------
; [cycle] operator repeat to infinitive the elements of the array
(def cycle-result (cycle [1, 2, 3]))
;(println cycle-result) // if you uncomment it will never end

; [repeat] operator allow you to repeat a sequence of array a specific number of times if you
; specify, or forever if you don't
(def repeat-result (repeat 3 [1, 2, 3, 4, 5]))
(println repeat-result)


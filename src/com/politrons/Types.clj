(ns com.politrons.Types
  ; In Clojure you can import as required in your namespace the namespace you want to use
  ; and give them an alias using [:as]
  (:require [clojure.string :as str]))

; Primitive types
;-----------------
;By default Clojure put a numeric value into a long, in case you want to specify the type you still can
; adding (type value)
;Int
;-----
(def integer-type (int 10))
(println integer-type)
(println (type integer-type))

;Long
;-----
(def long-type 1981)
(println long-type)
(println (type long-type))

;Double
;--------
(def double-type 10.1)
(println double-type)
(println (type double-type))

;Boolean
;--------
(def boolean-type true)
(println boolean-type)
(println (type boolean-type))

;String
;-------
; String API from namespace [clojure.string] it contains all typical operators of string type
(defn string-features [my-param]
  (println (clojure.string/includes? my-param "hello"))
  ; We can also use the string API of Java
  (println (.toUpperCase my-param))
  (println (str/blank? my-param))
  (println (str/ends-with? my-param "world"))
  (println (str/replace my-param "hello" "bye"))
  (println (str/split my-param #" "))
  (println (str/trim my-param))
  )

(string-features "hello world")

; List
;--------
; In Clojure in order to receive n elements you must use [& elements]
; something similar as in Java with [...] and must be the last argument of the function
(defn list-features [& elements]
  (def myList (list elements))
  myList)

(println "return list:" (list-features 1, 2, 3, 4, 5))

; Map
;--------
; In order to create map in Clojure is just so simple like define a structure with { :key value} where [:]
; are used to define keys
(def my-map {:key1 "politrons" :key2 1981 :foo 10.0})

(println "MyMap:" my-map)
;You can access the value of the map using directly the key value
(println "Map value:" (my-map :key1))

;Or you can access through a variable using [keyword] before the variable
(def my-key "key1")
(println "Map value through variable:" (my-map (keyword my-key)))

;  Casting
;-----------
; You can always cast a value from one type to other just specify the type when you set in a new variable.
; As we can see in this example, we are casting from double to integer, so once we adjust the value to int type,
; we lost the float point.
(def float-number 10.1)
(println (type float-number))
(def integer-number (int float-number))
(println "Double to Integer" (type integer-number))
(println "Integer value" integer-number)

;Same happens with numeric values into string, obviously it cannot be done in the other way around.
(def number-to-string 1981)
(def string-number (str number-to-string))
(println "String value" string-number)


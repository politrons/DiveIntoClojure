(ns com.politrons.Types
  ; In Clojure you can import as required in your namespace the namespace you want to use
  ; and give them an alias using [:as]
  (:require [clojure.string :as str]))

; String
;--------
; String API from namespace [clojure.string] it contains all typical operators of string type
(defn string-features [myParam]
  (println (clojure.string/includes? myParam "hello"))
  ; We can also use the string API of Java
  (println (.toUpperCase myParam))
  (println (str/blank? myParam))
  (println (str/ends-with? myParam "world"))
  (println (str/replace myParam "hello" "bye"))
  (println (str/split myParam #" "))
  (println (str/trim myParam))
  )

(string-features "hello world")

; List
;--------
; List API from namespace [clojure.list] it contains all typical operators of list type
(defn list-features [& myParam]
  ; We can also define a global variable inside a function
  (def myList (list myParam))
  myList
  )

(println (list-features 1, 2, 3, 4))



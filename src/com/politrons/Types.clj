(ns com.politrons.Types
  ; In Clojure you can import as required in your namespace the namespace you want to use
  ; and give them an alias using [:as]
  (:require [clojure.string :as str]))


; Types & Variables
; ------------------

; In Clojure you can define variables in your namespace using [def] in each place, and it will be consider as global variable
; Global variable
(def my-name "Politrons")

; List
;--------
; List API from namespace [clojure.list] it contains all typical operators of list type
(defn use-and-define-global-variable []
  ; We can also define a global variable inside a function
  (def innerGlobalVariable "global variable created inside function")
  )

(println my-name)

(use-and-define-global-variable)

(println innerGlobalVariable)

; String
;--------
; String API from namespace [clojure.string] it contains all typical operators of string type
(defn string-features [myParam]
  (println (clojure.string/includes? myParam "hello"))
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



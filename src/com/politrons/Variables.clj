(ns com.politrons.Variables
  ; In Clojure you can import as required in your namespace the namespace you want to use
  ; and give them an alias using [:as]
  (:require [clojure.string :as str]))

; Def
;--------
; In Clojure you can define variables in your namespace using [def] in each place,
; and it will be consider as global variable Global variable
(def my-name "Politrons")

; List API from namespace [clojure.list] it contains all typical operators of list type
(defn use-and-define-global-variable []
  ; We can also define a global variable inside a function
  (def innerGlobalVariable "global variable created inside function")
  )

(println my-name)

(use-and-define-global-variable)

(println innerGlobalVariable)

; Let
;--------
; In case you just want to create variables with a reduced scope to be used only in some computations,
; you can use [let]  and all variables created inside only can be used in the context of the let
(let [words ["Hello" "Variables" "in" "Clojure"]
      my-index 1
      word (get words my-index)]
  (println "selected word:" word "....."))



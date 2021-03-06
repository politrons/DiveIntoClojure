(ns com.politrons.Variables)
; All computations in Clojure must be inside ()

; [Def]
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

; [Let]
;--------
; In case you just want to create variables with a reduced scope to be used only in some computations,
; you can use [let [variables]]  and all variables created inside only can be used in the context of the let
(let [words ["Hello" "Variables" "in" "Clojure"]
      ; This variable is only defined inside the scope of [let]
      my-index 1
      word (get words my-index)]
  (println "selected word:" word "....."))

;(println my-index) //It wont compile since is not visible form outside

; Inside a defn you can define your [let] section and only inside this computation ()
; the variables define will be able to be used.
(defn local-variables-with-let []
  (let [my-local-variable "local value"]
    (println my-local-variable))
  )
(local-variables-with-let)

;(println my-my-local-variable) //It wont compile since is not visible form outside

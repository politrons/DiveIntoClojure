(ns com.politrons.Pipelines
  (:require [clojure.string :as str]))

; [Pipelines]
; -----------
; In clojure if we want to have composition of functions, we can use [->>] which provide the possibility
; to create pipelines as you could have in another functional languages.

; We can have a pipeline where we [filter] a couple times the collection, and then finally transform each element
; with [map] function
(def pipeline-filter (->> ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]
                          (filter (fn [word] (or (= "politrons" word) (= "hello" word))))
                          (filter (fn [word] (= "politrons" word)))
                          (map (fn [word] (str/upper-case word)))
                          ))

(println "Pipeline-filter:" pipeline-filter)

;Another possibility is to combine transformation with [map] then [filter] each element of the array
; and finally reduce the result into one string
(def pipeline-reduce (->> ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]
                          (map (fn [word] (str/upper-case word)))
                          (filter (fn [word] (> (count word) 5)))
                          (reduce (fn [acc next] (str acc "-" next)))
                          ))

(println "Pipeline-reduce:" pipeline-reduce)

; In case we pass a string into the [->>] function, it will create an array with each character
; of the String
(def pipeline-string (->> "hello pipeline world"
                          (map (fn [word] (str/upper-case word)))
                          ))

(println "Pipeline-string:" pipeline-string)

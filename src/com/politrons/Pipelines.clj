(ns com.politrons.Pipelines
  (:use [com.politrons.Types]
        :reload-all)
  ;To import defTypes from Types namespace
  (:import [com.politrons.Types Human])
  (:require [clojure.string :as str]))

(defn upper-case-func [] (fn [word] (str/upper-case word)))

; [Pipelines]
; -----------
; In clojure if we want to have composition of functions, we can use [->>] which provide the possibility
; to create pipelines as you could have in another functional languages.

; We can have a pipeline where we [filter] a couple times the collection, and then finally transform each element
; with [map] function
(def pipeline-filter (->> ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]
                          (filter (fn [word] (or (= "politrons" word) (= "hello" word))))
                          (filter (fn [word] (= "politrons" word)))
                          (map (upper-case-func))
                          ))

(println "Pipeline-filter:" pipeline-filter)

;Another possibility is to combine transformation with [map] then [filter] each element of the array
; and finally reduce the result into one string
(def pipeline-reduce (->> ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]
                          (map (upper-case-func))
                          (filter (fn [word] (> (count word) 5)))
                          (reduce (fn [acc next] (str acc "-" next)))
                          ))

(println "Pipeline-reduce:" pipeline-reduce)

; In case we pass a string into the [->>] function, it will create an array with each character
; of the String
(def pipeline-string (->> "hello pipeline world"
                          (map (upper-case-func))
                          ))

(println "Pipeline-string:" pipeline-string)

; If we want to make composition with another array, we can use [mapcat] in the pipeline.
(def pipeline-flat-map-filter (->> ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]
                                   (map (upper-case-func))
                                   (mapcat (fn [word] (conj ["$$$"] word)))
                                   ))
(println "Pipeline-flat-map:" pipeline-flat-map-filter)

; Apart from primitive types, we can also add into array data types to transform, filter
(def pipeline-deftype (->> [(Human. "Politron" 39 "male")]
                           (map (fn [human] (Human. (str/upper-case (str (.getName human))) 40 (.getSex human))))
                           (filter (fn [human] (= (str (.getName human)) "POLITRON")))
                           ))
(println "Pipeline-deftype:" (.toString (last pipeline-deftype)))
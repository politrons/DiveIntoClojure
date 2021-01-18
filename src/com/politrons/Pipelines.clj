(ns com.politrons.Pipelines
  (:require [clojure.string :as str]))



; [Pipelines]
; -----------

(def pipeline-result (->> ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]
                          (filter (fn [word] (or (= "politrons" word) (= "hello" word))))
                          (filter (fn [word] (= "politrons" word)))
                          (map (fn [word] (str/upper-case word)))
                          ))

(println "Pipeline-filter:" pipeline-result)


(def pipeline-fold (->> ["hello" "politrons" "welcome" "to" "functional" "lisp" ""]
                        (map (fn [word] (str/upper-case word)))
                        (filter (fn [word] (> (count word) 5)))
                        (reduce (fn [acc next] (str acc "-" next)))
                        ))

(println "Pipeline-reduce:" pipeline-fold)
(ns clojure-server.core-test
  (:require [clojure.test :refer :all]
            [clojure-server.core :refer :all]))

(deftest a-test
  (testing "Fixed, I fail."
    (is (= 1 1))))

(ns prime-multiplication.core-test
  (:require [clojure.test :refer :all]
            [prime-multiplication.core :refer :all]))

(deftest test-psieve
  (is (= (-> (psieve-init) (psieve-current)) 2))
  (is (= (-> (psieve-init) (psieve-next) (psieve-next) (psieve-current)) 5)))

(deftest test-gen-primes
  (is (= (gen-primes 0) []))
  (is (= (gen-primes 1) [2]))
  (is (= (gen-primes 10) [2 3 5 7 11 13 17 19 23 29]))
  (is (= (count (gen-primes 10000)) 10000)))

(deftest test-multiplication-table
  (is (= (multiplication-table []) []))
  (is (= (multiplication-table [2 3]) [{0 2, 2 4, 3 6}
                                       {0 3, 2 6, 3 9}])))

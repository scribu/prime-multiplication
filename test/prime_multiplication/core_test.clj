(ns prime-multiplication.core-test
  (:require [clojure.test :refer :all]
            [prime-multiplication.core :refer :all]))

(deftest test-prime?
  (is (= (prime? 0) false))
  (is (= (prime? 1) false))
  (is (= (prime? 2) true))
  (is (= (prime? 3) true))
  (is (= (prime? 4) false)))

(deftest test-gen-primes
  (is (= (gen-primes 0) []))
  (is (= (gen-primes 1) [2]))
  (is (= (gen-primes 5) [2 3 5 7 11])))

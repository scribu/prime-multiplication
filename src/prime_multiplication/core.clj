(ns prime-multiplication.core
  (:require [clojure.pprint :refer [print-table]])
  (:gen-class))

(defn- divisible [a b]
  (zero? (mod a b)))

; Infinite sieve logic ported from http://code.activestate.com/recipes/117119-sieve-of-eratosthenes/#c3
(defn prime-sieve
  "Returns an infinite sequence of primes."
  []
  (letfn [(sieve [[p & x]]
               (cons p (sieve (filter #(not (divisible % p)) x))))]
    (sieve (drop 2 (range)))))

(defn multiplication-table
  "Generate a multiplication table from a vector of numbers."
  [items]
  (for [x items]
    (let [row (for [y items] [y (* x y)])]
      (assoc (into (sorted-map) row) 0 x))))

(defn -main
  [in]
  (let [n (Integer/parseInt in 10)]
    (if (< n 0)
      (do
        (println "The number must be a positive integer.")
        (System/exit 1))
      (->> (prime-sieve)
           (take n)
           multiplication-table
           print-table))))

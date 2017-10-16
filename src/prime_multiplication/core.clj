(ns prime-multiplication.core
  (:require [clojure.pprint :refer [print-table]])
  (:gen-class))

(defn divisible? [a b]
  (zero? (mod a b)))

(defn gen-primes
  "Return a vector containing the first n prime numbers."
  [n]
  (loop [primes [2] x 3]
    (if (>= (count primes) n)
      (take n primes)
      (let [new-primes (if (not-any? (partial divisible? x) primes)
                         (conj primes x)
                         primes)]
        (recur new-primes (+ 2 x))))))

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
      (-> n
          gen-primes
          multiplication-table
          print-table))))

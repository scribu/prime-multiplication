(ns prime-multiplication.core
  (:require [clojure.pprint :refer [print-table]])
  (:gen-class))

(defn divisible? [a b]
  (zero? (mod a b)))

(defn gen-primes
  "Return a vector containing the first n prime numbers."
  [n]
  (letfn [(compute [primes x]
            (if (>= (count primes) n)
              (take n primes)
              (if (not-any? (partial divisible? x) primes)
                (compute (conj primes x) (+ 2 x))
                (compute primes (+ 2 x)))))]
    (compute [2] 3)))

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

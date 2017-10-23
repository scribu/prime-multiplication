(ns prime-multiplication.core
  (:require [clojure.pprint :refer [print-table]])
  (:gen-class))

; Infinite sieve logic ported from answer by Will Ness: https://stackoverflow.com/a/19391111/97998
(defn psieve-init
  "Initialise a prime sieve."
  []
  {:last 2 :precomputed [3], :i 5, :D {}})

(defn psieve-current
  "Return the current value computed by the sieve."
  [state]
  (state :last))

(defn psieve-next
  "Find the next prime."
  [state]
  (if-let [[x & xs] (state :precomputed)]
    (assoc state :last x :precomputed xs)
    (let [find-next-i (fn [i step D]
                        (loop [j i]
                          (if (not (contains? D j))
                            j
                            (recur (+ step j)))))
         update-step (fn [D i step]
                        (let [j (find-next-i (+ step i) step D)]
                          (assoc D j step)))]
      (loop [i (state :i)
             D (state :D)
             ps (state :ps (psieve-next (psieve-init)))]
        (let [p (psieve-current ps)
              step (D i)]
          (if (not (nil? step)) ; composite
            (recur (+ 2 i) (update-step (dissoc D i) i step) ps)
            (if (< i (* p p)) ; prime
              {:last i, :i (+ 2 i), :D D, :ps ps}
              (do ; composite, = p*p
                  (assert (= i (* p p)))
                  (let [new-step (* 2 (psieve-current ps))
                        new-ps (psieve-next ps)]
                    (recur (+ 2 i) (update-step D i new-step) new-ps))))))))))

(defn prime-sieve
  "Returns an infinite sequence of primes."
  []
  (map psieve-current (iterate psieve-next (psieve-init))))

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

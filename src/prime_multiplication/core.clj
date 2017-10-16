(ns prime-multiplication.core
  (:gen-class))

(defn divisible? [a b]
  (zero? (mod a b)))

(defn prime? [n]
  (and (> n 1) (not-any? (partial divisible? n) (range 2 n))))

(defn gen-primes
  "Returns a list of the first n prime numbers."
  [n]
  (letfn [(compute [acc x]
            (if (= (count acc) n)
              acc
              (if (prime? x)
                (compute (conj acc x) (inc x))
                (compute acc (inc x)))))]
    (compute [] 2)))

(defn -main
  [in]
  (let [n (Integer/parseInt in 10)]
    (if (< n 0)
      (do
        (println "The number must be a positive integer.")
        (System/exit 1))
      (println "Number:" n))))

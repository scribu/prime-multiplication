(ns prime-multiplication.core
  (:gen-class))

(defn gen-primes
  "Returns a list of the first n prime numbers."
  [n]
  [])

(defn -main
  [in]
  (let [n (Integer/parseInt in 10)]
    (if (< n 0)
      (do
        (println "The number must be a positive integer.")
        (System/exit 1))
      (println "Number:" n))))

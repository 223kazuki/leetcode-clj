(ns leetcode-clj.problem385)

(defn deserialize [s]
  (if-not (= (first s) \[)
    [(Integer/parseInt s)]
    (let [s (filter #(not= \, %) s)
          num? #{\- \0 \1 \2 \3 \4 \5 \6 \7 \8 \9}]
      (loop [s s stack [[]]]
        (if (empty? s)
          (first (peek stack))
          (case (first s)
            \[ (recur (rest s) (conj stack []))
            \] (recur (rest s) (-> stack pop pop vec
                                   (conj (-> stack pop peek vec (conj (peek stack))))))
            (recur (drop-while num? s)
                   (let [n (->> (take-while num? s)
                                (apply str)
                                (Integer/parseInt))]
                     (-> stack pop vec (conj [n]))))))))))

(and
 (= (deserialize "123") [123])
 (= (deserialize "[123]") [123])
 (= (deserialize "[-123]") [-123])
 (= (deserialize "[123,[456]]") [123 [456]])
 (= (deserialize "[123,[456,[789]]]") [123 [456 [789]]])
 (= (deserialize "[123,[456],[789]]") [123 [456] [789]]))

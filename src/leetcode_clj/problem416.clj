(ns leetcode-clj.problem416)

(defn can-partition [nums]
  (let [sum (reduce + nums)]
    (if-not (zero? (mod sum 2))
      false
      (let [target (/ sum 2)]
        (loop [queue (-> clojure.lang.PersistentQueue/EMPTY
                         (conj {:index 0 :amount (first nums)}))]
          (if (empty? queue)
              false
              (let [{:keys [:index :amount]} (peek queue)]
                (cond
                  (== amount target) true
                  (== index (dec (count nums))) (recur (pop queue))
                  :else
                  (let [new-amount (->> index
                                        (inc)
                                        (nth nums)
                                        (+ amount))
                        queue (-> queue
                                  pop
                                  (conj {:index (inc index) :amount amount}))]
                    (recur (if (<= new-amount target)
                             (-> queue
                                 (conj {:index (inc index) :amount new-amount}))
                             queue)))))))))))

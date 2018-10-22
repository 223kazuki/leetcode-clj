(ns leetcode-clj.problem638)

(defn next-node [offer-amount rest-needs price offer]
  (let [offer-price (last offer)
        rest-needs (->> (butlast offer)
                        (map - rest-needs))]
    (when (empty? (filter neg? rest-needs))
      {:rest-needs (vec rest-needs)
       :offer-amount (+ offer-amount offer-price)
       :amount (+ offer-amount offer-price
                  (->> price
                       (map * rest-needs)
                       (reduce +)))})))

(defn next-queue [queue offer-amount rest-needs price offers]
  (loop [queue (pop queue)
         offers offers]
    (if (empty? offers)
      queue
      (recur (if-let [next (next-node offer-amount rest-needs price (first offers))]
               (conj queue next) queue)
             (rest offers)))))

(defn shopping-offers
  [price special needs]
  (loop [result (->> price
                     (map * needs)
                     (reduce +))
         queue (conj clojure.lang.PersistentQueue/EMPTY
                     {:rest-needs needs :offer-amount 0 :amount result})]
    (if-let [{:keys [:rest-needs :offer-amount :amount]} (peek queue)]
      (let [queue (next-queue queue offer-amount rest-needs price special)
            result (min result amount)]
        (recur result queue))
      result)))

(= (next-node 0 [3 2] [2 5] [3 0 5])
   {:rest-needs [0 2] :offer-amount 5 :amount 15})

(= (next-node 0 [1 2 1] [2 3 4] [2 2 1 9])
   nil)

(= (next-node 0 [1 2 1] [2 3 4] [1 1 0 4])
   {:rest-needs [0 1 1] :offer-amount 4 :amount 11})

(= (next-node 1 [1 2 1] [2 3 4] [1 1 0 4])
   {:rest-needs [0 1 1] :offer-amount 5 :amount 12})

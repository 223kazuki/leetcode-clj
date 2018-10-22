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

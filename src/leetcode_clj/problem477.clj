(ns leetcode-clj.problem477)

#_ (def hammig-distance
     (memoize
      (fn [x1 x2]
        (->> (bit-xor x1 x2)
             (Integer/bitCount)))))

#_ (defn total-hamming-distance [nums]
     (loop [i 0 result 0]
       (if (== i (count nums))
         result
         (recur (inc i)
                (->> (range i)
                     (map #(hammig-distance (nth nums %) (nth nums i)))
                     (reduce +)
                     (+ result))))))

(defn total-hamming-distance [nums]
  (->> (range 32)
       (map (fn [bit]
              (let [zero-count (->> nums
                                    (map #(bit-shift-right % bit))
                                    (map #(bit-and % 1))
                                    (filter zero?)
                                    count)]
                (* zero-count (- (count nums) zero-count)))))
       (reduce +)))

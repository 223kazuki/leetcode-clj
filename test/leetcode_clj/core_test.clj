(ns leetcode-clj.core-test
  (:require [clojure.test :refer :all]
            [leetcode-clj.core :refer :all]
            [clojure.string :as str]
            [leetcode-clj.problem385 :as p385]
            [leetcode-clj.problem477 :as p477]
            [leetcode-clj.problem638 :as p638]))

(deftest problem385-test
  (is (= (p385/deserialize "123") [123]))
  (is (= (p385/deserialize "[123]") [123]))
  (is (= (p385/deserialize "[-123]") [-123]))
  (is (= (p385/deserialize "[123,[456]]") [123 [456]]))
  (is (= (p385/deserialize "[123,[456,[789]]]") [123 [456 [789]]]))
  (is (= (p385/deserialize "[123,[456],[789]]") [123 [456] [789]])))

(deftest problem385-test
  (is (= (p638/next-node 0 [3 2] [2 5] [3 0 5])
       {:rest-needs [0 2] :offer-amount 5 :amount 15}))
  (is (= (p638/next-node 0 [1 2 1] [2 3 4] [2 2 1 9])
         nil))
  (is (= (p638/next-node 0 [1 2 1] [2 3 4] [1 1 0 4])
         {:rest-needs [0 1 1] :offer-amount 4 :amount 11}))
  (is (= (p638/next-node 1 [1 2 1] [2 3 4] [1 1 0 4])
         {:rest-needs [0 1 1] :offer-amount 5 :amount 12}))
  (is (== (p638/shopping-offers [2,5] [[3,0,5],[1,2,10]] [3,2]) 14))
  (is (== (p638/shopping-offers [2,3,4] [[1,1,0,4],[2,2,1,9]] [1,2,1]) 11)))

(ns leetcode-clj.core-test
  (:require [clojure.test :refer :all]
            [leetcode-clj.core :refer :all]
            [clojure.string :as str]
            [leetcode-clj.problem385 :as p385]
            [leetcode-clj.problem477 :as p477]))

(deftest problem385-test
  (is (= (p385/deserialize "123") [123]))
  (is (= (p385/deserialize "[123]") [123]))
  (is (= (p385/deserialize "[-123]") [-123]))
  (is (= (p385/deserialize "[123,[456]]") [123 [456]]))
  (is (= (p385/deserialize "[123,[456,[789]]]") [123 [456 [789]]]))
  (is (= (p385/deserialize "[123,[456],[789]]") [123 [456] [789]])))

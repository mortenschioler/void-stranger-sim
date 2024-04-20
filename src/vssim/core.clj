(ns vssim.core)

(def square-types #{:square/empty :square/plain})


(defn random-square-types
  []
  (random-sample 0.5 (cycle square-types))) ; temporary impl

(defn level-outline
  [dim]
  (let [[X Y] dim
        i (volatile! -1)]
    (vec
     (for [x (range X)
           y (range Y)]
       {:i (vswap! i inc)
        :x x
        :y y}))))


(defn add-random-squares
  [level]
  (update level :squares
          (fn [squares]
            (mapv #(assoc % :square-type %2)
                  squares
                  (random-square-types)))))

(defn empty-level 
  [dim]
  (let [outline (level-outline dim)]
    {:dim dim
     :size (count outline)
     :squares outline}))

(defn random-level
  [dim]
  (-> (empty-level dim)
      (add-random-squares)
      ))

(comment

  (level-outline [4 4])
  (random-level [3 3]))


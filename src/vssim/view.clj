(ns vssim.view 
  (:require [clojure.string :as str]))

(def square-type-str-reps
  {:square/empty "⬛"
   :square/plain "⬜"
   :square/player "🦸"
   :square/stairs "🪜"})

(defn render-square
  [square]
  (or (square-type-str-reps (:square-type square))
      (throw (ex-info (format "No such square type: \"%s\"" (:square-type square)) {}))))

(defn render-level
  [level]
  (let [[x _y] (:dim level)]
    (str (str/join "\n"
              (->> (partition x (:squares level))
                   (map render-square)
                   (str/join)))
         "\n")))
;; 

(comment
  
  (print (render-square {:square-type :square/empty}))
  
  )
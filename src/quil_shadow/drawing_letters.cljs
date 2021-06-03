
(ns quil-shadow.drawing-letters
  (:require [quil.core :as q :include-macrso true]))

(defn update-state [state]
  state)

(defn draw [state]
  (q/no-fill)
  (doseq [[ind t] [[1 -8] [2 -1] [3 0] [4 1] [5 5]]]
    (q/curve-tightness t)
    (q/with-translation [100 (+ 200 (* ind 100))]
        (q/curve 0 0 0 0 50 30 100 -30)
        (q/curve 0 0 50 30 100 -30 150 0)
        (q/curve 50 30 100 -30 150 0 150 0))))


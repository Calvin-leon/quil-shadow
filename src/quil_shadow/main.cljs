(ns quil-shadow.main
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [quil-shadow.computer-toys :as d]))

;obsolete code = main is directed to computer_toys with /d

(q/defsketch sketch
  :host "host"
  :size [4000 900]
  :setup d/setup
  :draw d/draw
  :update d/update-state
  :middleware [m/fun-mode])
  
(defn main! [])
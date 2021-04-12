(ns quil-shadow.computer-toys
  (:require [quil.core :as q :include-macros true]))


(defn setup []
  (js/console.log "setup")
  (q/set-state! :image (q/load-image "/letter_a_png/letter_a.gif"))
  (q/frame-rate 60)
  (q/background 200))


(defn update-state [state]
  state)

(defn draw [state]
  (q/no-stroke)
  (js/console.log state)
  (q/no-stroke)
  (let [image (q/state :image)]
    (when (q/loaded? image) (q/image image 0 0))))
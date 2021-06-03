(ns quil-shadow.computer-toys
  (:require [quil.core :as q :include-macros true]))

(defn setup []
  (q/frame-rate 100)
  (q/background 255) ; Background früher callen und wie mache ich die animation schneller?
  (js/console.log "setup")
  
  #_(q/set-state! :images {:a (q/load-image "/letter_a_png/letter_a.gif")})) ;für a - Jetzt für den Rest (:a)
;maybe ad songs
; create url to load image 100x100
#_
(let [; create url to load image 100x100
      url "/letter_a_png/letterA_.png"]
  (q/set-state! :image (q/load-image url)))

(q/set-state! :images {:a (q/load-image "/letter_a_png/letterA_.png")})

(defn draw []
  (q/no-fill)
  ; try different tightnesses
  (doseq [[ind t] [[0 -8] [2 -1] [1 0] [4 1] [5 5]]]
    (q/curve-tightness t)
    (q/with-translation [100 (+ 200 (* ind 100))]
      (q/curve 0 0 0 0 50 30 100 -30)
      (q/curve 0 0 50 30 100 -30 150 0)
      (q/curve 50 30 100 -30 150 0 150 0))))

(defn update-state [state] 
  state)

#_
(q/defsketch my
  :host "host"
  :size [800 800]
  :draw draw)


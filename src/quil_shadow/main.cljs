(ns quil-shadow.main
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [quil-shadow.computer-toys :as d]))

(defn pulse [low high rate millis]
  (let [diff (- high low)
        half (/ diff 2)
        mid (+ low half)
        s (/ millis 1000.0)
        x (q/sin (* s (/ 1.1 rate)))]
    (+ mid (* x half))))

;[This means = position 1 block is [a b] meaning on which position]
; Commenting with ; semicolon
; defining letters with coordinates:

(def letter-C [;[0 0]
               ;LetterC
               [3 2] [2 3] [2 4] [2 5] [2 6] [3 7]
               [4 8] [5 8]
               [6 7]
               [6 2]
               [5 1] [4 1]
               ;Line 
               ;[0 9] [1 9] [2 9] [3 9] [4 9] [5 9] [6 9] [7 9] [8 9 ] [9 9] [10 9 ] [11 9 ] [12 9] [13 9 ] [13 9] [14 9]
               ;Letter O
               [10 2] [9 3] [9 4] [9 5] [9 6] [10 7]
               [11 8] [12 8]
               [13 7]
               [14 6] [14 5] [14 4] [14 3]
               [13 2] [12 1] [11 1]
               ;letter M
               [17 1] [17 2] [17 3] [17 4] [17 5] [17 6] [17 7] [17 8]
               [18 2] [19 3] [20 2]
               [21 1] [21 2] [21 3] [21 4] [21 5] [21 6] [21 7] [21 8]
               ;letter P
               [24 1] [24 2] [24 3] [24 4] [24 5] [24 6] [24 7] [24 8]
               [25 1] [26 1] [27 2] [27 3] [26 4] [25 4]
               ;letter U
               [30 1] [30 2] [30 3] [30 4] [30 5] [30 6] [30 7]
               [31 8] [32 8] [33 8] 
               [34 1] [34 2] [34 3] [34 4] [34 5] [34 6] [34 7]
               ;letter T
               [39 8] [39 7] [39 6] [39 5] [39 4] [39 3] [39 2] [39 1]
               [37 1] [38 1] [40 1] [41 1]
               ;letter E
               [44 8] [44 7] [44 6] [44 5] [44 4] [44 3] [44 2] [44 1]
               [45 1] [46 1] [47 1] [45 4] [46 4] [45 8] [46 8] [47 8]
               ;letter R
               [50 1] [50 2] [50 3] [50 4] [50 5] [50 6] [50 7] [50 8]
               [51 1] [52 1] [53 2] [53 3] [52 4] [51 4] [51 5] [52 6] [53 7] [54 8]
               ;Game
               ;letter G
               ;[6 12] [5 11] [4 11] [3 12]
               ;[2 13] [2 14] [2 15] [2 16]
               ;[3 17] [4 18] [5 18] [6 17] [6 16] [6 15] [5 15]
               ;letter A
               ;[10 11] [11 11] [9 18] [9 17] [9 16] [9 15] [9 14] [9 13] [9 12]
               ;[12 12] [12 13] [12 14] [12 15] [12 16] [12 17] [12 18] [11 15] [10 15]
               ;letter M2
               ;[15 18] [15 17] [15 16] [15 15] [15 14] [15 13] [15 12] [15 11]
               ;[16 12] [17 13] [18 12] [19 11] [19 12] [19 13] [19 14] [19 15] [19 16] [19 17] [19 18]
               ;letter E
               ;[22 11] [22 12] [22 13] [22 14] [22 15] [22 16] [22 17] [22 18]
               ;[23 18] [24 18] [25 18] [23 14] [24 14] [23 11] [24 11] [25 11]
               ;letter S
               ;[28 13] [28 12] [29 11] [30 11] [31 12]
               ;[29 14] [30 15] [31 16] [31 17] [30 18] [29 18] [28 17]
               
               ;Toys
               ;letter T
               [2 11] [3 11] [4 11] [5 11] [6 11] 
               [4 12] [4 13] [4 14] [4 15] [4 16] [4 17] [4 18]
               ;O
               [10 12] [9 13] [9 14] [9 15] [9 16] [10 17]
               [11 18] [12 18]
               [13 17]
               [14 16] [14 15] [14 14] [14 13]
               [13 12] [12 11] [11 11]
               ;letter Y
               [17 11] [18 12] [19 13] [19 14] [19 15] [19 16] [19 17] [19 18] [20 12] [21 11]
               ;letter S
               [24 13] [24 12] [25 11] [26 11] [27 12]
               [25 14] [26 15] [27 16] [27 17] [26 18] [25 18] [24 17]
               ])

(def step 30)

(defn create-color [millis]
  [(pulse 0 255 3.0 millis)
   (pulse 0 255 5.0 millis)
   (pulse 0 255 7.0 millis)])

(defn setup []
  (q/frame-rate 60)
  (q/background 255)
  (zipmap letter-C
          (map create-color (iterate #(+ % 120) 10000))))

(defn get-lamp-index []
  (if (and (< 0 (q/mouse-x) (q/width))
           (< 0 (q/mouse-y) (q/height)))
    [(quot (q/mouse-x) step)
     (quot (q/mouse-y) step)]
    nil))

(defn update-state [state]
  (if-let [index (get-lamp-index)]
    (assoc state index (create-color (q/millis)))
    state))

(defn draw-state [state]
  (q/no-stroke)
  (q/background 0)
  (q/no-stroke) 
  (let [w (q/width)
        h (q/height)
        hw (/ w 2)
        hh (/ h 2)]
    (doseq [[ind col] state]
      (let [x (* step (first ind))
            y (* step (second ind))
            col-mod (-> (+ x y (q/millis))
                        (* 0.02)
                        (q/sin)
                        (* 3))]
        (apply q/fill (map + col (repeat 3 col-mod)))
        (q/rect x y step step)))))


(q/defsketch sketch
  :host "host"
  :size [500 500]
  :setup d/setup
  :draw d/draw
  :update d/update-state
  :middleware [m/fun-mode])
  
(defn main! [])
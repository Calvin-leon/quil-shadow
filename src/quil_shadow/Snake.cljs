(ns quil-shadow.Snake
  (:import
   (java.awt Color Dimension)
   (javax.swing Jpanel JFrame Timer JOptionPane)
   (java.awt.event ActionListener KeyListener KeyEvent)))


;constants
(def field-with 50) 
(def field-height 30)
(def point-site 15)
(def turn-millis 100)
(def win-length 100)
(def directions
  {KeyEvent/VK_LEFT [-1 0]
   KeyEvent/VK_RIGHT [1 0]
   KeyEvent/VK_UP [0 -1]
   KeyEvent/VK_DOWN [0 1]})


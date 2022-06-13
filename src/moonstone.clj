(ns moonstone)

; UNIQUE – STARLIT GRACE: When affecting champions with attacks or abilities in combat,
; Heal power icon.png heal the nearby allied champion (excluding yourself) with the most missing health for 60 (2 second cooldown).
;Each second spent in combat with champions grants 5% heal and shield power, stacking up to 5 times for maximum of 25%.
;MYTHIC PASSIVE: Empowers each of your other Legendary items with 8 heal for this item's passive effect, Starlit Grace
(def moonstone {:base-heal 60.0
                :heal-bonus-per-sec 0.05
                :base-cd 2.0
                :ingenious-hunter-cd 1.35
                :ap 40})

(def revitalize {:high-hp 1.05
                 :low-hp 1.10})



(defn moonstone-healing [sec]
  (let [procs (int (/ sec (:base-cd moonstone)))
        fast-procs (int (/ sec (:ingenious-hunter-cd moonstone)))]
    (println "moonstone base procs" procs "\t moonstone fast procs" fast-procs)
    {:base-moonstone (* procs (:base-heal moonstone))
     :ingenious-hunter-heal (* fast-procs (:base-heal moonstone))
     :revitalize-heal-highhp (* procs (:base-heal moonstone) (:high-hp revitalize))
     :revitalize-heal-hplow (* procs (:base-heal moonstone) (:low-hp revitalize))}))

;; reasonable AH at lvl 13 is 70. Ionian + Transcedne + Chem purifier + Moonstone
;; This translates to 41.18% CDR
(defn shielding [ap sec cdr]
  (let [procs (int (/ sec (* (- 1 cdr) 8)))                 ;; 8 magic number is cd of lvl 5 shield
        base (* procs (+ 240 (* 0.45 ap)))]
    (println "shield procs " procs)
    {:base base
     :revitalize-hphigh (* base 1.05)
     :revitalize-hplow (* base 1.10)}))

(defn best [ap sec cdr]
  {:red   (+ (:base (shielding ap sec cdr)) (:ingenious-hunter-heal (moonstone-healing sec)))
   :green (+ (:revitalize-hplow (shielding ap sec cdr)) (:revitalize-heal-hplow (moonstone-healing sec)))})
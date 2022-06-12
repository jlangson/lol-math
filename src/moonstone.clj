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
    (println procs " " fast-procs)
    [ {:base-moonstone (* procs (:base-heal moonstone))}
     {:ingenious-hunter-heal (* fast-procs (:base-heal moonstone))}
     {:revitalize-heal-highhp (* procs (:base-heal moonstone) (:high-hp revitalize))}
     {:revitalize-heal-hplow (* procs (:base-heal moonstone) (:low-hp revitalize))}
     ]))
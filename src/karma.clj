(ns karma)

(def abilities {:e {:base 240
                    :ap 0.45}})
; UNIQUE – STARLIT GRACE: When affecting champions with attacks or abilities in combat,
; Heal power icon.png heal the nearby allied champion (excluding yourself) with the most missing health for 60 (2 second cooldown).
;Each second spent in combat with champions grants 5% heal and shield power, stacking up to 5 times for maximum of 25%.
;MYTHIC PASSIVE: Empowers each of your other Legendary items with 8 heal for this item's passive effect, Starlit Grace
(def moonstone {:base-heal 60
                :max-heal
                :base-cd
                :ap 40})
package com.game

import java.awt.Color
import java.awt.Graphics


class HUD {
    private var greenValue = 255f
    private var redValue = 0f
    var score = 0
    var level = 1


    fun tick() {


        HEALTH = Game.clamp(HEALTH, 0F, 100F)
        greenValue = Game.clamp(greenValue, 0F, 255F)
        redValue = Game.clamp(redValue, 0F, 255F)
        greenValue = HEALTH * 2
        redValue = (100 - HEALTH) * 2

        score++
    }

    fun render(g: Graphics) {
        g.setColor(Color.gray)
        g.fillRect(15, 15, 200, 32)
        g.setColor(Color(redValue.toInt(), greenValue.toInt(), 0))
        g.fillRect(15, 15, HEALTH.toInt() * 2, 32)
        g.setColor(Color.white)
        g.drawRect(15, 15, 200, 32)

        g.drawString("Score: $score", 15, 64)
        g.drawString("Level: $level", 15, 80)
    }

    companion object {

        var HEALTH = 100f
        fun setLevel(hud: HUD, i: Int) {
            hud.level=i
        }

        fun setScore(hud: HUD, i: Int) {
            hud.score=i
    
        }

        fun getScore(hud: HUD): Int {
            return hud.score
    
        }

        fun getLevel(hud: HUD): Int {
            return hud.level
        }
    }
}
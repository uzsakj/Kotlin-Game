package com.game

import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle

class Player(x: Int, y: Int, id: ID, internal var handler: Handler) : GameObject(x, y, id) {
    override fun getBounds(): Rectangle = Rectangle(x.toInt(), y.toInt(), 32, 32)

    override fun tick() {
        x += velX
        y += velY

        x = Game.clamp(x, 0F, (Game.WIDTH - 37).toFloat())
        y = Game.clamp(y, 0F, (Game.HEIGHT - 66).toFloat())

        collision()
    }

    private fun collision() {
        for (i in 0 until handler.`object`.size) {
            val tempObject = handler.`object`[i]
            if (getId(tempObject) === ID.BasicEnemy || getId(tempObject) === ID.FastEnemy || getId(
                    tempObject
                ) === ID.SmartEnemy) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2
                }
            }
        }
    }

    override fun render(g: Graphics) {
        g.color = Color.white
        g.fillRect(x.toInt(), y.toInt(), 32, 32)
    }


}

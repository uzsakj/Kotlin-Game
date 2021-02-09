package com.game

import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle

class BasicEnemy(x: Int, y: Int, id: ID, private val handler: Handler) : GameObject(x, y, id) {
    override fun getBounds(): Rectangle {
        return Rectangle(x.toInt(), y.toInt(), 16, 16)
    }



    init {

        velX = 5f
        velY = 5f
    }

    override fun tick() {
        x += velX
        y += velY

        if (y <= 0 || y >= Game.HEIGHT - 48) velY *= -1f
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1f
        handler.addObject(Trail(x, y, ID.Trail, Color.red, 15, 15, 0.05f, handler))
    }

    override fun render(g: Graphics) {
        g.color = Color.red
        g.fillRect(x.toInt(), y.toInt(), 16, 16)
    }
}

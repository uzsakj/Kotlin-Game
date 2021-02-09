package com.game

import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle

class FastEnemy(x: Int, y: Int, id: ID, private val handler: Handler) : GameObject(x, y, id) {
    override fun getBounds(): Rectangle = Rectangle(x.toInt(), y.toInt(), 16, 16)

    init {

        velX = 3F
        velY = 7F
    }

    override fun tick() {
        x += velX
        y += velY

        if (y <= 0 || y >= Game.HEIGHT - 48) velY *= -1
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1
        handler.addObject(
            Trail(x, y, ID.Trail, Color.cyan, 15, 15, 0.05f, handler)
        )
    }

    override fun render(g: Graphics) {
        g.color = Color.red
        g.fillRect(x.toInt(), y.toInt(), 16, 16)
    }
}

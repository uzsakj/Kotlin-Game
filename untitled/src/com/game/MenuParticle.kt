package com.game

import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import java.util.Random

class MenuParticle(x: Int, y: Int, id: ID, private val handler: Handler) : GameObject(x, y, id) {
    private var r = Random()

    private val col: Color
    override fun getBounds(): Rectangle = Rectangle(x.toInt(), y.toInt(), 16, 16)

    init {

        velX = (r.nextInt(7 - -7) + -7).toFloat()
        velY = (r.nextInt(7 - -7) + -7).toFloat()
        if (velX == 0F) velX = 1F
        if (velY == 0F) velY = 1F

        col = Color(r.nextInt(255), r.nextInt(255), r.nextInt(255))
    }

    override fun tick() {
        x += velX
        y += velY

        if (y <= 0 || y >= Game.HEIGHT - 48) velY *= -1
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1
        handler.addObject(
            Trail(x, y, ID.Trail, col, 15, 15, 0.05f, handler)
        )
    }

    override fun render(g: Graphics) {
        g.color = col
        g.fillRect(x.toInt(), y.toInt(), 16, 16)
    }
}

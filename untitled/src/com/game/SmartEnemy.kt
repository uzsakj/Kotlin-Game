package com.game

import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle

class SmartEnemy(x: Int, y: Int, id: ID, private val handler: Handler) : GameObject(x, y, id) {
    private var player: GameObject? = null

    override fun getBounds(): Rectangle = Rectangle(x.toInt(), y.toInt(), 16, 16)

    init {
        for (i in 0 until handler.`object`.size) {
            if (Companion.getId(handler.`object`[i]) === ID.Player) {
                player = handler.`object`[i]
            }
        }
    }

    override fun tick() {
        x += velX
        y += velY

        val diffX = x - getX(player!!) - 16
        val diffY = y - getY(player!!) - 16
        val distance = Math.sqrt(
            ((x - getX(player!!)) * (x - getX(player!!)) + (y - getY(
                player!!
            )) * (y - getY(player!!))).toDouble()
        ).toFloat()

        velX = -1 / distance * diffX
        velY = -1 / distance * diffY

        //if(y<=0 ||y>=Game.HEIGHT-48) velY *=-1;
        //if(x<=0 ||x>=Game.WIDTH-16) velX *=-1;
        handler.addObject(
            Trail(x, y, ID.Trail, Color.green, 15, 15, 0.05f, handler)
        )
    }

    override fun render(g: Graphics) {
        g.color = Color.green
        g.fillRect(x.toInt(), y.toInt(), 16, 16)
    }
}

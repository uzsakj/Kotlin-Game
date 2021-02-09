package com.game

import java.awt.AlphaComposite
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle

class Trail(
    x: Float,
    y: Float,
    id: ID,
    private val color: Color,
    private val width: Int,
    private val height: Int,
    private val life: Float,
    private val handler: Handler
) : GameObject(x.toInt(), y.toInt(), id) {

    private var alpha = 1f

    override fun tick() {
        if (alpha > life) {
            alpha -= life - 0.01f
        } else
            handler.removeObject(this)
    }

    override fun render(g: Graphics) {
        val g2d = g as Graphics2D
        g2d.composite = makeTransparent(alpha)
        g.setColor(color)
        g.fillRect(x.toInt(), y.toInt(), width, height)
        g2d.composite = makeTransparent(1f)
    }

    private fun makeTransparent(alpha: Float): AlphaComposite {
        val type = AlphaComposite.SRC_OVER
        return AlphaComposite.getInstance(type, alpha)

    }

    override fun getBounds(): Rectangle? {
        return null
    }

}
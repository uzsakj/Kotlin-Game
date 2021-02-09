package com.game

import java.awt.Graphics
import java.awt.Rectangle

abstract class GameObject(x: Int, y: Int, var id: ID) {

    var x: Float = 0.toFloat()
        protected set
    var y: Float = 0.toFloat()
        protected set
    var velX: Float = 0.toFloat()
        protected set
    var velY: Float = 0.toFloat()
        protected set

    abstract fun getBounds(): Rectangle?

    init {
        this.x = x.toFloat()
        this.y = y.toFloat()
    }

    fun setVelX(velX: Int) {
        this.velX = velX.toFloat()
    }

    fun setVelY(velY: Int) {
        this.velY = velY.toFloat()
    }

    fun setX(x: Int) {
        this.x = x.toFloat()
    }

    fun setY(y: Int) {
        this.y = y.toFloat()
    }

    abstract fun tick()
    abstract fun render(g: Graphics)


    companion object {
        fun getId(gameObject: GameObject): ID {
            return gameObject.id;
    
        }

        fun getX(gameObject: GameObject): Float {
            return gameObject.x

        }

        fun getY(gameObject: GameObject): Float {
            return gameObject.y
    
        }
    }

}

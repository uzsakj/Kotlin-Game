package com.game

import java.awt.Color
import java.awt.Graphics
import java.util.ArrayList

class Handler {

    internal var `object` = ArrayList<GameObject>()

    fun tick() {
        try {
            for (i in 0 until `object`.size) {
                val tempObject = `object`[i]
                tempObject.tick()
            }

        } catch (e: Exception) {

        }

    }

    fun render(g: Graphics) {

        try {
            for (i in 0 until `object`.size) {
                val tempObject = `object`[i]
                tempObject.render(g)
            }

        } catch (e: Exception) {

        }

    }

    fun addObject(`object`: GameObject) {
        this.`object`.add(`object`)
    }

    fun removeObject(`object`: GameObject) {
        this.`object`.remove(`object`)
    }

    fun clearEnemys() {
        try {
            for (i in 0 until `object`.size) {
                val tempObject = `object`[i]
                if (GameObject.getId(tempObject) == ID.Player) {
                    `object`.clear()
                    addObject(
                        Player(
                            GameObject.getX(tempObject).toInt(),
                            GameObject.getY(tempObject).toInt(),
                            ID.Player,
                            this
                        )
                    )
                }
            }
        } catch (e:IndexOutOfBoundsException) {
        }

    }
}

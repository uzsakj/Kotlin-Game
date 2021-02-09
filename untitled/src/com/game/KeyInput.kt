package com.game

import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyInput(private val handler: Handler) : KeyAdapter() {
    private val keyDown = BooleanArray(4)

    init {
        keyDown[0] = false
        keyDown[1] = false
        keyDown[2] = false
        keyDown[3] = false
    }


    override fun keyPressed(e: KeyEvent) {
        val key = e.getKeyCode()

        for (i in 0 until handler.`object`.size) {
            val tempObject = handler.`object`.get(i)
            if (GameObject.getId(tempObject) === ID.Player) {
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-5)
                    keyDown[0] = true
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(5)
                    keyDown[1] = true
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(5)
                    keyDown[2] = true
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5)
                    keyDown[3] = true
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0)
        }
    }

    override fun keyReleased(e: KeyEvent) {
        val key = e.keyCode
        for (i in 0 until handler.`object`.size) {
            val tempObject = handler.`object`[i]
            if (GameObject.getId(tempObject) === ID.Player) {
                if (key == KeyEvent.VK_W) {
                    keyDown[0] = false
                }
                if (key == KeyEvent.VK_S) {
                    keyDown[1] = false
                }
                if (key == KeyEvent.VK_D) {
                    keyDown[2] = false
                }
                if (key == KeyEvent.VK_A) {
                    keyDown[3] = false
                }

                if (!keyDown[0] && !keyDown[1]) {
                    tempObject.setVelY(0)
                }
                if (!keyDown[2] && !keyDown[3]) {
                    tempObject.setVelX(0)
                }
            }

        }
    }
}

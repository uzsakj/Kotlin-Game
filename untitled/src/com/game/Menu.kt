package com.game

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.Random

import com.game.Game.STATE

class Menu(private val game: Game, private val handler: Handler, private val hud: HUD) : MouseAdapter() {
    private val r = Random()
    override fun mousePressed(e: MouseEvent) {
        val mx = e.x
        val my = e.y

        if (game.gameState == STATE.Menu) {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 60, 200, 50)) {
                game.gameState = STATE.Game
                handler.addObject(
                    Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler)
                )
                handler.clearEnemys()
                handler.addObject(
                    BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler)
                )
            }

            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 135, 200, 50)) {
                game.gameState = STATE.Help
            }


            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 210, 200, 50)) {
                System.exit(0)
            }

        }
        if (game.gameState == STATE.Help) {
            if (mouseOver(mx, my, 210, 350, 200, 50)) {
                game.gameState = STATE.Menu
                return
            }
        }
        if (game.gameState == STATE.End) {

            if (mouseOver(mx, my, 210, 350, 200, 50)) {
                game.gameState = STATE.Game
                HUD.setLevel(hud, 1)
                HUD.setScore(hud, 0)
                handler.addObject(
                    Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler)
                )
                handler.clearEnemys()
                handler.addObject(
                    BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler)
                )
            }
        }

    }

    override fun mouseReleased(e: MouseEvent) {

    }

    fun tick() {

    }

    private fun mouseOver(mx: Int, my: Int, x: Int, y: Int, width: Int, height: Int): Boolean {
        return if (mx > x && mx < x + width) {
            my > y && my < y + height
        } else
            false
    }

    fun render(g: Graphics) {

        if (game.gameState == STATE.Menu) {
            val fnt = Font("arial", 1, 50)
            val fnt1 = Font("arial", 1, 30)

            g.font = fnt
            g.color = Color.white
            g.drawString("Menu", Game.WIDTH / 2 - 60, 50)

            g.font = fnt1
            g.drawRect(Game.WIDTH / 2 - 100, 60, 200, 50)
            g.drawString("Play", Game.WIDTH / 2 - 30, 95)

            g.drawRect(Game.WIDTH / 2 - 100, 135, 200, 50)
            g.drawString("Help", Game.WIDTH / 2 - 30, 170)

            g.drawRect(Game.WIDTH / 2 - 100, 210, 200, 50)
            g.drawString("Quit", Game.WIDTH / 2 - 30, 245)
        } else if (game.gameState == STATE.Help) {
            val fnt = Font("arial", 1, 50)
            val fnt1 = Font("arial", 1, 30)
            g.font = fnt
            g.color = Color.white
            g.drawString("Help", Game.WIDTH / 2 - 60, 50)


            g.drawString("Use WASD to play!",120,200)

            g.font = fnt1
            g.color = Color.white
            g.drawRect(210, 350, 200, 50)
            g.drawString("Back", 270, 385)
        } else if (game.gameState === STATE.End) {
            val fnt = Font("arial", 1, 50)
            val fnt1 = Font("arial", 1, 30)
            g.font = fnt
            g.color = Color.white
            g.drawString("Game Over", 180, 70)

            g.font = fnt1
            g.drawString("You Lost with a score of: " + HUD.getScore(hud), 175, 200)

            g.font = fnt1
            g.color = Color.white
            g.drawRect(210, 350, 200, 50)
            g.drawString("Try again", 245, 390)
        }
    }
}


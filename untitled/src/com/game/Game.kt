package com.game

import java.awt.Canvas
import java.awt.Color
import java.util.Random

class Game : Canvas(), Runnable {
    private var thread: Thread? = null
    private var running = false

    private val handler: Handler
    private val hud: HUD
    private val spawn: Spawn
    private val r: Random
    private val menu: Menu

    var gameState = STATE.Menu

    enum class STATE {
        Menu,
        Help,
        Game,
        End
    }

    init {
        handler = Handler()
        hud = HUD()
        menu = Menu(this, handler, hud)
        this.addKeyListener(KeyInput(handler))
        this.addMouseListener(menu)
        Window(WIDTH, HEIGHT, "Game", this)
        spawn = Spawn(handler, hud)
        r = Random()
        if (gameState == STATE.Game) {
            handler.addObject(
                Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler)
            )
            handler.addObject(
                BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler)
            )
        } else {
            for (i in 0..19) {
                handler.addObject(
                    MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler)
                )
            }
        }

    }

    @Synchronized
    fun start() {
        thread = Thread(this)
        thread!!.start()
        running = true
    }

    @Synchronized
    fun stop() {
        try {
            thread!!.join()
            running = false

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    override fun run() {
        var lastTime = System.nanoTime()
        val amountOfTicks = 60.0
        val ns = 1000000000 / amountOfTicks
        var delta = 0.0
        var timer = System.currentTimeMillis()
        var frames = 0
        while (running) {

            val now = System.nanoTime()
            delta += (now - lastTime) / ns
            lastTime = now
            while (delta >= 1) {
                tick()
                delta--
            }
            if (running)
                render()
            frames++

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000
                System.out.println("FPS: $frames")
                frames = 0
            }
        }
        stop()
    }

    private fun tick() {
        handler.tick()
        if (gameState == STATE.Game) {
            hud.tick()
            spawn.tick()
            if (HUD.HEALTH <= 0) {
                HUD.HEALTH = 100F
                handler.clearEnemys()
                gameState = STATE.End
                for (i in 0..19) {
                    handler.addObject(
                        MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler)
                    )
                }


            }
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
            menu.tick()
        }

    }

    private fun render() {
        this.requestFocus()
        val bs = this.bufferStrategy
        if (bs == null) {
            this.createBufferStrategy(3)
            return
        }
        val g = bs.drawGraphics

        g.color = Color.black
        g.fillRect(0, 0, WIDTH, HEIGHT)

        handler.render(g)
        if (gameState == STATE.Game) {
            hud.render(g)
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
            menu.render(g)
        }
        g.dispose()
        bs.show()
    }

    companion object {

        private val serialVersionUID = 1L

        val WIDTH = 640
        val HEIGHT = WIDTH / 12 * 9

        fun clamp(x: Float, min: Float, max: Float): Float {
            if (x >= max) {
                return max
            } else if (x <= min) {
                return min
            } else {
                return x
            }
        }


    }


}
fun main(args: Array<String>) {
    Game()
}

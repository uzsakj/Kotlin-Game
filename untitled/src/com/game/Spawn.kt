package com.game

import java.util.Random

class Spawn(private val handler: Handler, private val hud: HUD) {
    private var scoreKeep = 0
    private val r = Random()

    fun tick() {
        scoreKeep++

        if (scoreKeep >= 500) {
            scoreKeep = 0
            HUD.setLevel(hud, HUD.getLevel(hud) + 1)

            if (HUD.getLevel(hud) == 2) {
                handler.addObject(
                    BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler)
                )
            }
            if (HUD.getLevel(hud) == 3) {
                handler.addObject(
                    BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler)
                )
            }
            if (HUD.getLevel(hud) == 4) {
                handler.addObject(
                    FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler)
                )
            }
            if (HUD.getLevel(hud) == 5) {
                handler.addObject(
                    BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler)
                )
            }
            if (HUD.getLevel(hud) == 6) {
                handler.addObject(
                    SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler)
                )
            }
            if (HUD.getLevel(hud) == 7) {
                handler.addObject(
                    FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler)
                )
            }
            if (HUD.getLevel(hud) == 10) {
                handler.clearEnemys()
                handler.addObject(
                    EnemyBoss(Game.WIDTH / 2 - 48, -120, ID.EnemyBoss, handler)
                )

            }

        }

    }
}

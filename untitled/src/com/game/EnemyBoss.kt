package com.game

import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import java.util.Random

class EnemyBoss(x: Int, y: Int, id: ID, private val handler: Handler) : GameObject(x, y, id) {
    private var r = Random()
    private var timer1 = 150
    private var timer2 = 50

    init {

        velX = 0f
        velY = 2f
    }

    override fun getBounds(): Rectangle? {
        return Rectangle(x.toInt(), y.toInt(), 16, 16)
    }

    override fun tick() {
        x += velX
        y += velY

        timer1--
        if (timer1 < 0)
            velY = 0f
        else
            timer1--

        if (timer1 < 0) timer2--
        if (timer2 <= 0) {
            if (velX == 0f) velX = 3f
            if (velX > 0)
                velX += 0.007f
            else if (velX < 0)
                velX -= 0.007f

            velX = Game.clamp(velX, (-10).toFloat(), 10F)
            val spawn = r.nextInt(10)
            if (spawn == 0) handler.addObject(EnemyBossBullet(x.toInt() + 48, y.toInt() + 48, ID.BasicEnemy, handler))
        }


        //if(y<=0 ||y>=Game.HEIGHT-48) velY *=-1;
        if (x <= 0 || x >= Game.WIDTH - 96) velX *= -1f
        //handler.addObject(new Trail(x,y,ID.Trail,Color.red,95,95, 0.05f,handler));
    }

    override fun render(g: Graphics) {
        g.color = Color.red
        g.fillRect(x.toInt(), y.toInt(), 96, 96)
    }
}

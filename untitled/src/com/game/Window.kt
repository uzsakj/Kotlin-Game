package com.game

import java.awt.Canvas
import java.awt.Dimension

import javax.swing.JFrame

class Window(width: Int, height: Int, title: String, game: Game) : Canvas() {

    init {
        val frame = JFrame(title)

        frame.preferredSize = Dimension(width, height)
        frame.minimumSize = Dimension(width, height)
        frame.maximumSize = Dimension(width, height)

        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.add(game)
        frame.isVisible = true
        game.start()
    }

    companion object {

        private const val serialVersionUID = 1L
    }

}
 
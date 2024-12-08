package com.felipeabella.proyectocorte2

class Snake {
    companion object {

        var headX = 0f
        var headY = 0f
        var bodyParts =
            mutableListOf(arrayOf(0f, 0f))
        var direction = "right";
        var alive = false;
        var score: Int = 0

        fun possibleMove(): Boolean {
            if (headX < 0f || headX > 1000f || headY < 0f || headY > 1000)
                return false
            return true
        }

        fun reset() {
            headX = 0f;
            headY = 0f;
            bodyParts = mutableListOf(arrayOf(0f, 0f))
            direction = "right";
        }
    }
}
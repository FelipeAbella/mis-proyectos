package com.felipeabella.proyectocorte2

class Food {
    companion object {
        var posX = 500f
        var posY = 500f

        fun generate() {
            posX = (1..20).random().toFloat() * 50
            posY = (1..20).random().toFloat() * 50
        }
    }
}
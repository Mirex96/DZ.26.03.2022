package com.example.dz26032022

object IdGenerator  {
    private var id = 0
    fun generateId() : Int {
        id += 1
        return id
    }
}
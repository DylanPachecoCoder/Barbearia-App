package com.example.android.lesprojeto.data.model

import java.io.Serializable

data class Barber(
    val id: Int = 0,
    val name: String,
    var apelido: String,
) : Serializable

package com.unsoed.responsi1mobileh1d023025.data.model

data class TeamResponse(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val area: Area,
    val coach: Coach?,
    val squad: List<Player>
)

data class Area(
    val name: String
)

data class Coach(
    val name: String?,
    val nationality: String?,
    val dateOfBirth: String?
)

data class Player(
    val name: String?,
    val position: String?,
    val nationality: String?,
    val dateOfBirth: String?
)

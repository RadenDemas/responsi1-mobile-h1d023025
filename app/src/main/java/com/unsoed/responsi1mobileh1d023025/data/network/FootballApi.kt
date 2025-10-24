package com.unsoed.responsi1mobileh1d023025.data.network

import com.unsoed.responsi1mobileh1d023025.data.model.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FootballApi {
    @GET("teams/{id}")
    suspend fun getTeamInfo(
        @Header("X-Auth-Token") apiKey: String,
        @Path("id") teamId: Int
    ): TeamResponse
}

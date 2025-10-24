package com.unsoed.responsi1mobileh1d023025.data.repository

import com.unsoed.responsi1mobileh1d023025.data.model.TeamResponse
import com.unsoed.responsi1mobileh1d023025.data.network.ApiClient

class TeamRepository {
    suspend fun getTeamInfo(apiKey: String, teamId: Int): TeamResponse {
        return ApiClient.api.getTeamInfo(apiKey, teamId)
    }
}

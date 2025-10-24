package com.unsoed.responsi1mobileh1d023025.view.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsoed.responsi1mobileh1d023025.data.model.TeamResponse
import com.unsoed.responsi1mobileh1d023025.data.repository.TeamRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(private val repository: TeamRepository) : ViewModel() {

    private val _team = MutableLiveData<TeamResponse?>()
    val team: LiveData<TeamResponse?> get() = _team

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchTeamInfo(apiKey: String, teamId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getTeamInfo(apiKey, teamId)
                _team.postValue(response)
                _error.postValue(null)
            } catch (e: HttpException) {
                _error.postValue("HTTP error: ${e.code()} ${e.message()}")
            } catch (e: Exception) {
                _error.postValue("Unexpected error: ${e.message}")
            }
        }
    }
}

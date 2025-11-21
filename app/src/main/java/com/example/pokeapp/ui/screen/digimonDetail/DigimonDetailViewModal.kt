package com.example.pokeapp.ui.screen.digimonDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.api.response.DigimonDetailResponse
import com.example.pokeapp.repository.MonsterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class DigimonDetailIntent {
    data class InitDetailPage(val id: Int) : DigimonDetailIntent()
}

class DigimonDetailViewModal(
    private val repository: MonsterRepository = MonsterRepository()
) : ViewModel() {

    private val _digimonModel = MutableStateFlow<DigimonModel>(DigimonModel.getEmptyModel(false))
    val digimonModel: StateFlow<DigimonModel> = _digimonModel

    fun handleIntent(intent: DigimonDetailIntent) {
        viewModelScope.launch {
            when (intent) {
                is DigimonDetailIntent.InitDetailPage -> {
                    getDigimonDetail(intent.id)
                }
            }
        }
    }

    private suspend fun getDigimonDetail(id: Int) {
        try {
            val response = repository.getDigimonDetail(id)
            handleDigimonDetail(response)
        } catch (_: Exception) {
            _digimonModel.value = DigimonModel.getEmptyModel(true)
        }
    }

    private fun handleDigimonDetail(response: DigimonDetailResponse) {
         _digimonModel.value = DigimonModel(
            false,
            response.id,
            response.name,
            response.images[0].href,
            response.types[0].type, // DIGIMON 的 第一頁是 page 0
            response.attributes[0].attribute,
             response.descriptions[0].description,
            response.descriptions[1].description
        )
    }
}

data class DigimonModel(
    val isApiError: Boolean,
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: String,
    val attributes: String,
    val descriptionJP: String,
    val descriptionEN: String
) {
    companion object {
        fun getEmptyModel(isApiError: Boolean): DigimonModel {
            return DigimonModel(
                isApiError,
                0,
                "",
                "",
                "",
                "",
                "",
                ""
            )
        }
    }
}
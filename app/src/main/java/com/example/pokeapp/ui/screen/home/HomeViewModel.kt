package com.example.pokeapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.BuildConfig
import com.example.pokeapp.api.response.DigimonListResponse
import com.example.pokeapp.api.response.PokemonListResponse
import com.example.pokeapp.repository.MonsterRepository
import com.example.pokeapp.util.SharedPref
import com.example.pokeapp.util.SharedPrefKeys
import com.example.pokeapp.util.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class HomeIntent {
    object Reload : HomeIntent()
    data class SelectedPage(val page: Int) : HomeIntent()
    data class ToggleShowType(val type: ShowType) : HomeIntent()
}

enum class ShowType {
    Grid,
    List
}

class HomeViewModel(
    private val repository: MonsterRepository = MonsterRepository()
) : ViewModel() {

    private val _homeModel = MutableStateFlow<HomeModel>(HomeModel.getEmptyModel(false))
    val homeModel: StateFlow<HomeModel> = _homeModel
    private val _showType = MutableStateFlow<ShowType>(ShowType.Grid)
    val showType: StateFlow<ShowType> = _showType

    init {
        getShowType()
        viewModelScope.launch {
            getMonstersData(1)
        }
    }

    fun handleIntent(intent: HomeIntent) {
        viewModelScope.launch {
            when (intent) {
                is HomeIntent.Reload -> {
                    _homeModel.value = HomeModel.getEmptyModel(false)
                    getMonstersData(1)
                }
                is HomeIntent.SelectedPage -> {
                    getMonstersData(intent.page)
                }
                is HomeIntent.ToggleShowType -> {
                    toggleShowType(intent.type)
                }
            }
        }
    }

    private suspend fun getMonstersData(page: Int) {
        when (BuildConfig.API_TYPE) {
            "POKEMON" -> {
                try {
                    val offset = page * 20 - 20
                    val response = repository.getPokemons(offset)
                    handlePokemonData(response)
                } catch (_: Exception) {
                    _homeModel.value = HomeModel.getEmptyModel(true)
                }
            }
            "DIGIMON" -> {
                try {
                    val response = repository.getDigimons(page-1) // DIGIMON 的 第一頁是 page 0
                    handleDigimonData(response)
                } catch (_: Exception) {
                    _homeModel.value = HomeModel.getEmptyModel(true)
                }
            }
        }
    }

    private fun handlePokemonData(response: PokemonListResponse) {
        val idList: MutableList<Int> = mutableListOf()
        val nameList: MutableList<String> = mutableListOf()
        val imageUrlList: MutableList<String> = mutableListOf()

        response.results.map { item ->
            val id = item.url.trimEnd('/').split("/").last() // 去掉最後的斜線再分割再取最後值
            idList.add(id.toInt())
            nameList.add(item.name.replaceFirstChar { c -> c.uppercase() })
            imageUrlList.add(Utils.getPokemonImageUrl(id.toInt()))
        }

        val totalPages = (response.count + 20 - 1) / 20

        _homeModel.value = HomeModel(
            false,
            idList,
            nameList,
            imageUrlList,
            getCurrentPage(response.previous),
            totalPages,
        )
    }

    private fun handleDigimonData(response: DigimonListResponse) {
        val idList: MutableList<Int> = mutableListOf()
        val nameList: MutableList<String> = mutableListOf()
        val imageUrlList: MutableList<String> = mutableListOf()

        response.content.map { item ->
            idList.add(item.id)
            nameList.add(item.name.replaceFirstChar { c -> c.uppercase() })
            imageUrlList.add(item.image)
        }

        _homeModel.value = HomeModel(
            false,
            idList,
            nameList,
            imageUrlList,
            response.pageable.currentPage+1, // DIGIMON 的 第一頁是 page 0
            response.pageable.totalPages+1, // DIGIMON 有第0頁
        )
    }

    private fun getCurrentPage(previous: String?, limit: Int = 20): Int {
        return if (previous == null) {
            1
        } else {
            // 從 previous URL 拿 offset
            val prevOffset = Regex("offset=(\\d+)").find(previous)?.groupValues?.get(1)?.toInt() ?: 0
            (prevOffset / limit) + 2
        }
    }

    private fun toggleShowType(type: ShowType) {
        _showType.value = type
        SharedPref.putString(SharedPrefKeys.SHOW_TYPE, type.name)
    }

    private fun getShowType() {
        _showType.value = ShowType.valueOf(SharedPref.getString(SharedPrefKeys.SHOW_TYPE, ShowType.Grid.name))
    }
}

data class HomeModel(
    val isApiError: Boolean,
    val monsterIdList: List<Int>,
    val monsterNameList: List<String>,
    val monsterImageUrlList: List<String>,
    val currentPage: Int,
    val totalPage: Int
) {
    companion object {
        fun getEmptyModel(isApiError: Boolean): HomeModel {
            return HomeModel(
                isApiError,
                emptyList(),
                emptyList(),
                emptyList(),
                0,
                0
            )
        }
    }
}
package com.example.pokeapp.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pokeapp.BuildConfig
import com.example.pokeapp.ui.view.ErrorView
import com.example.pokeapp.ui.screen.home.view.FilterTabView
import com.example.pokeapp.ui.view.LoadingView
import com.example.pokeapp.ui.screen.home.view.MonsterGridView
import com.example.pokeapp.ui.screen.home.view.MonsterListView
import com.example.pokeapp.navigation.Routes
import com.example.pokeapp.ui.view.AppTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = viewModel()) {

    val homeModel by viewModel.homeModel.collectAsState()
    val showType by viewModel.showType.collectAsState()

    val gridState = rememberLazyGridState()
    val listState = rememberLazyListState()

    val scope = rememberCoroutineScope()

    val appTitle = when (BuildConfig.API_TYPE) {
        "POKEMON" -> "Pokémon List"
        "DIGIMON" -> "Digimon List"
        else -> "Monster List"
    }

    fun goToMonsterDetailPage(navController: NavHostController, monsterId: Int, monsterName: String) {
        when (BuildConfig.API_TYPE) {
            "POKEMON" -> navController.navigate(Routes.POKEMON_DETAIL_SCREEN + "/$monsterId/$monsterName")
            "DIGIMON" -> navController.navigate(Routes.DIGIMON_DETAIL_SCREEN + "/$monsterId/$monsterName")
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(navController, appTitle, true)
        }
    ) { padding ->
        if (homeModel.isApiError) {
            ErrorView(onReloadButtonClicked = {
                viewModel.handleIntent(HomeIntent.Reload)
            })
        } else if (homeModel.monsterNameList.isEmpty()) {
            LoadingView()
        } else {
            Column(
                modifier = Modifier.padding(padding)
            ) {
                FilterTabView(
                    showType = showType,
                    onTypeChange = { type ->
                        viewModel.handleIntent(HomeIntent.ToggleShowType(type))
                        scope.launch {
                            gridState.scrollToItem(0)
                            listState.scrollToItem(0)
                        }
                    },
                    pageCount = homeModel.totalPage,
                    selectedPage = homeModel.currentPage,
                    onPageSelected = { page ->
                        viewModel.handleIntent(HomeIntent.SelectedPage(page))
                        scope.launch {
                            gridState.scrollToItem(0)
                            listState.scrollToItem(0)
                        }
                    }
                )
                when (showType) {
                    ShowType.Grid -> {
                        MonsterGridView(
                            modifier = Modifier.weight(1f),
                            gridState = gridState,
                            monsterNameList = homeModel.monsterNameList,
                            monsterImageUrlList = homeModel.monsterImageUrlList,
                            onMonsterClicked = { index ->
                                goToMonsterDetailPage(
                                    navController,
                                    homeModel.monsterIdList[index],
                                    homeModel.monsterNameList[index]
                                )
                            }
                        )
                    }
                    ShowType.List -> {
                        MonsterListView(
                            modifier = Modifier.weight(1f),
                            listState = listState,
                            monsterNameList = homeModel.monsterNameList,
                            monsterImageUrlList = homeModel.monsterImageUrlList,
                            onMonsterClicked = { index ->
                                goToMonsterDetailPage(
                                    navController,
                                    homeModel.monsterIdList[index],
                                    homeModel.monsterNameList[index]
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
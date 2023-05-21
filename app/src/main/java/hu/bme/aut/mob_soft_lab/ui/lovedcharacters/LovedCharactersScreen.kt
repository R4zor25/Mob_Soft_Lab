package hu.bme.aut.mob_soft_lab.ui.lovedcharacters

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.bme.aut.mob_soft_lab.navigation.MobSoftAppScreen
import hu.bme.aut.mob_soft_lab.ui.characterlist.CharacterListUiAction
import hu.bme.aut.mob_soft_lab.ui.characterlist.CharacterListViewModel
import hu.bme.aut.mob_soft_lab.ui.components.bottomnavigationbar.BottomNavigationBar
import hu.bme.aut.mob_soft_lab.ui.components.characterlistitem.CharacterListItem
import hu.bme.aut.mob_soft_lab.ui.components.topbar.TopBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LovedCharactersScreen(navController: NavController) {
    val viewModel = hiltViewModel<LovedCharactersViewModel>()
    val viewState by viewModel.viewState.collectAsState()
    val listState = rememberLazyListState()


    Scaffold(backgroundColor = Color.DarkGray,
        topBar = { TopBar("Loved Character List", leftIconImage = null, rightIconImage = {}, {}, {}) },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
    ) {
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                items(viewState.characterList.size) { i ->
                    val item = viewState.characterList[i]
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(4.dp))
                        CharacterListItem(gotCharacter = item, onListItemClick = {
                            navController.currentBackStackEntry?.arguments?.putParcelable("GotCharacter", it)
                            navController.navigate(MobSoftAppScreen.CharacterDetailsNav.route)
                        })
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
        }
    }
}
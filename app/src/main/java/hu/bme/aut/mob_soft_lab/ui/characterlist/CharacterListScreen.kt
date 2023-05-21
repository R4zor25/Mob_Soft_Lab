package hu.bme.aut.mob_soft_lab.ui.characterlist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.mob_soft_lab.navigation.MobSoftAppScreen
import hu.bme.aut.mob_soft_lab.ui.components.bottomnavigationbar.BottomNavigationBar
import hu.bme.aut.mob_soft_lab.ui.components.characterlistitem.CharacterListItem
import hu.bme.aut.mob_soft_lab.ui.components.topbar.TopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharacterListScreen(navController: NavController){
    val viewModel = hiltViewModel<CharacterListViewModel>()
    val listState = rememberLazyListState()
    val viewState by viewModel.viewState.collectAsState()


    Scaffold(
        modifier = Modifier.background(Color.LightGray),
        topBar = { TopBar("Character List", leftIconImage = null, rightIconImage = {}, {}, {}) },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.DarkGray) {
            LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                items(viewState.characterList.size) { i ->
                    val item = viewState.characterList[i]
                    if (i >= viewState.characterList.size - 1 && !viewState.endReached) {
                        viewModel.onAction(CharacterListUiAction.GetNextPage)
                    }
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
}
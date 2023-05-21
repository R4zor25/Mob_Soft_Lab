package hu.bme.aut.mob_soft_lab.ui.characterdetails

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.bme.aut.mob_soft_lab.model.GotCharacter
import hu.bme.aut.mob_soft_lab.navigation.MobSoftAppScreen
import hu.bme.aut.mob_soft_lab.ui.characterlist.CharacterListViewModel
import hu.bme.aut.mob_soft_lab.ui.components.bottomnavigationbar.BottomNavigationBar
import hu.bme.aut.mob_soft_lab.ui.components.topbar.TopBar
import hu.bme.aut.mob_soft_lab.ui.login.LoginOneShotEvent
import hu.bme.aut.mob_soft_lab.ui.login.LoginUiAction
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharacterDetailsScreen(navController: NavController, gotCharacter: GotCharacter?){
    val viewModel = hiltViewModel<CharacterDetailsViewModel>()
    val viewState by viewModel.viewState.collectAsState()
    var isLoved by rememberSaveable { mutableStateOf(viewModel.isCharacterLoved(gotCharacter))}

    LaunchedEffect("key") {
        viewModel.oneShotEvent
            .onEach {
                when (it) {
                    CharacterDetailsOneShotEvent.CharacterStateChanged -> {
                        isLoved = viewModel.isCharacterLoved(gotCharacter)
                    }
                    is CharacterDetailsOneShotEvent.ShowToastMessage -> TODO()
                }
            }
            .collect()
    }

    Scaffold(
        modifier = Modifier.background(Color.LightGray),
        topBar = { TopBar("Character Details", leftIconImage = Icons.Filled.ArrowLeft, rightIconImage = {}, {
            navController.navigateUp()
        }, {}) },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.DarkGray
        ) {
            Column(modifier = Modifier.padding(bottom = 8.dp, top = 4.dp, end = 8.dp, start = 16.dp)) {
                Text(text = "Name: ${gotCharacter?.name?.ifBlank { "Unknown" }}", color = Color.LightGray)
                Text(
                    text = "Aliases: ${
                        if (gotCharacter?.aliases?.isNotEmpty() == true) {
                            gotCharacter.aliases.toString()
                        } else "Unknown"
                    }", color = Color.LightGray
                )
                Text(
                    text = "Titles: ${
                        if (gotCharacter?.titles?.isNotEmpty() == true && gotCharacter.titles[0].isNotBlank()) {
                            gotCharacter.titles.toString()
                        } else "Unknown"
                    }", color = Color.LightGray
                )
                Text(text = "Born: ${gotCharacter?.born?.ifBlank { "Unknown" }}", color = Color.LightGray)
                Text(text = "Died: ${gotCharacter?.died?.ifBlank { "Unknown" }}", color = Color.LightGray)
                Text(text = "Culture: ${gotCharacter?.culture?.ifBlank { "Unknown" }}", color = Color.LightGray)
                Text(
                    text = "Played By: ${
                        if (gotCharacter?.playedBy?.isNotEmpty() == true && gotCharacter.playedBy[0].isNotBlank()) {
                            gotCharacter.playedBy.toString()
                        } else "Unknown"
                    }", color = Color.LightGray
                )
                Text(
                    text = "tvSeries: ${
                        if (gotCharacter?.tvSeries?.isNotEmpty() == true && gotCharacter.tvSeries[0].isNotBlank()) {
                            gotCharacter.tvSeries.toString()
                        } else "Unknown"
                    }", color = Color.LightGray
                )
                androidx.compose.material.Button(
                    onClick = {
                        viewModel.onAction(CharacterDetailsUiAction.HandleLovedButtonPress(gotCharacter!!))
                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray
                    )
                ) {
                    Text(text = if(isLoved) "Remove from loved" else "Add to loved", color = Color.Black)
                }
            }
        }
    }
}
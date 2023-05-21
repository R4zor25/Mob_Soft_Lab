package hu.bme.aut.mob_soft_lab.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.bme.aut.mob_soft_lab.navigation.MobSoftAppScreen
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val view = LocalView.current
    var userName by remember { mutableStateOf("") }


    LaunchedEffect("key") {
        viewModel.oneShotEvent
            .onEach {
                when (it) {
                    LoginOneShotEvent.NavigateToCharacterList -> navController.navigate(MobSoftAppScreen.CharacterListNav.route) {
                        navController.popBackStack()
                    }
                    else -> {}
                }
            }
            .collect()
    }

    Surface(modifier = Modifier
        .fillMaxSize(), color = Color.DarkGray) {
        Column(
            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Sign In", modifier = Modifier.align(Alignment.CenterHorizontally), style = TextStyle(
                    color = Color.Black,
                    fontSize = 32.sp,
                    fontWeight = FontWeight(700)
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(.9f),
                shape = RoundedCornerShape(30.dp),
                value = userName,
                onValueChange = { value ->
                    userName = value
                },
                label = { Text(text = "Username") },
                textStyle = MaterialTheme.typography.subtitle1,
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { userName = "" }) {
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    textColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    trailingIconColor = Color.Black
                ),
                keyboardActions = KeyboardActions(onDone = {
                    view.clearFocus()
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )
            Button(onClick = {
                viewModel.onAction(LoginUiAction.Login(userName))
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.LightGray
            )) {
                Text(text = "Log in")
            }

        }
    }


}
package hu.bme.aut.mob_soft_lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.mob_soft_lab.model.GotCharacter
import hu.bme.aut.mob_soft_lab.navigation.MobSoftAppScreen
import hu.bme.aut.mob_soft_lab.ui.characterdetails.CharacterDetailsScreen
import hu.bme.aut.mob_soft_lab.ui.characterlist.CharacterListScreen
import hu.bme.aut.mob_soft_lab.ui.login.LoginScreen
import hu.bme.aut.mob_soft_lab.ui.lovedcharacters.LovedCharactersScreen
import hu.bme.aut.mob_soft_lab.ui.theme.Mob_Soft_LabTheme



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mob_Soft_LabTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = MobSoftAppScreen.LoginScreenNav.route
                    ) {
                        composable(
                            route = MobSoftAppScreen.LoginScreenNav.route
                        ) {
                            LoginScreen(navController)
                        }
                        composable(
                            route = MobSoftAppScreen.CharacterListNav.route
                        ) {
                            CharacterListScreen(navController)
                        }
                        composable(
                            route = MobSoftAppScreen.LovedCharacterListNav.route
                        ) {
                            LovedCharactersScreen(navController)
                        }
                        composable(
                            route = MobSoftAppScreen.CharacterDetailsNav.route
                        ) {
                            val gotCharacter = navController.previousBackStackEntry?.arguments?.getParcelable<GotCharacter>("GotCharacter")
                            CharacterDetailsScreen(navController, gotCharacter)
                        }
                    }
                }
            }
        }
    }
}

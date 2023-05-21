package hu.bme.aut.mob_soft_lab.navigation

sealed class MobSoftAppScreen(val route: String){
    object LoginScreenNav : MobSoftAppScreen("login")
    object CharacterListNav: MobSoftAppScreen("character_list")
    object LovedCharacterListNav: MobSoftAppScreen("loved_character_list")
    object CharacterDetailsNav: MobSoftAppScreen("character_details")
}

class Navigation {
}
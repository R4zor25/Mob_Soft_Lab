package hu.bme.aut.mob_soft_lab.ui.components.characterlistitem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import hu.bme.aut.mob_soft_lab.model.GotCharacter

@Composable
fun CharacterListItem(gotCharacter: GotCharacter, onListItemClick: (GotCharacter) -> Unit) {

    Surface(modifier = Modifier
        .clickable { onListItemClick.invoke(gotCharacter) }
        .padding(horizontal = 4.dp, vertical = 4.dp)
        .fillMaxWidth(),
        color = Color.LightGray ,
        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp, bottomEnd = 20.dp, bottomStart = 20.dp)) {
        Column(modifier = Modifier.padding(bottom = 8.dp, top = 4.dp, end = 8.dp, start = 16.dp)) {
            Text(text = "Name: ${gotCharacter.name.ifBlank { "Unknown" }}", color = Color.Black)
            Text(text = "Aliases: ${if(gotCharacter.aliases.isNotEmpty()) { gotCharacter.aliases.toString() } else "Unknown"}", color = Color.Black)
            Text(text = "Born: ${gotCharacter.born.ifBlank { "Unknown" }}", color = Color.Black)
            Text(text = "Died: ${gotCharacter.died.ifBlank { "Unknown" }}", color = Color.Black)
        }
    }
}
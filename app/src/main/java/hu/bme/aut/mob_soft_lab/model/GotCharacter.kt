package hu.bme.aut.mob_soft_lab.model

data class GotCharacter(
    val aliases: List<String> = emptyList(),
    val allegiances: List<String> = emptyList(),
    val books: List<String> = emptyList(),
    val born: String = "",
    val culture: String = "",
    val died: String = "",
    val father: String = "",
    val mother: String = "",
    val name: String = "",
    val playedBy: List<String> = emptyList(),
    val povBooks: List<String> = emptyList(),
    val spouse: String = "",
    val titles: List<String> = emptyList(),
    val tvSeries: List<String> = emptyList(),
    val url: String = ""
)
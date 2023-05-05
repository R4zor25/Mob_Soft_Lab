package hu.bme.aut.mob_soft_lab.model

data class Character(
    val aliases: List<String>,
    val allegiances: List<String>,
    val books: List<String>,
    val born: String,
    val culture: String,
    val died: String,
    val father: String,
    val mother: String,
    val name: String,
    val playedBy: List<String>,
    val povBooks: List<Any>,
    val spouse: String,
    val titles: List<String>,
    val tvSeries: List<String>,
    val url: String
)
package uz.mufassal.apihomework.network.data

data class WordsResponseItem(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
)
package uz.mufassal.apihomework.network.data

data class WordResponse(
    val word: String? = "",
    val phonetic: String? = "",
    val phonetics: List<WordResponsePhonetics>?,
    val origin: String? = "",
    val meanings: List<WordResponseMeanings>?,
)

data class WordResponsePhonetics(
    val text: String? = "",
    val audio: String? = "",
)

data class WordResponseMeanings(
    val partOfSpeech: String? = "",
    val definitions: List<WordResponseDefinitions>?,
)

data class WordResponseDefinitions(
    val definition: String? = "",
    val example: String? = "",
    val synonyms: List<String>?,
    val antonyms: List<String>?,
)


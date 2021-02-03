package wisefox.com.spaceodysseyserver.model


data class QuestionBean(
    val quest_id: Long,
    val quest_content: String,
    val quest_answer1: String,
    val quest_answer2: String,
    val quest_answer3: String?,
    val quest_answer4: String?,
    val quest_explanation: String,
    val level: Level,
    val theme: Theme,
)

data class Level(
    val lvl_id: Byte,
    val lvl_name: String,
)

data class Theme(
    val theme_id: Byte,
    val theme_name: String,
)

data class ResponseCodeBean<T>(
    val code: Int,
    val message: String?,
    val data: T? = null
)
package wisefox.com.spaceodysseyserver.model

import javax.persistence.*

@Entity
data class Question(
    @Id val quest_id: Int,
    val quest_content: String,
    val quest_answer1: String,
    val quest_answer2: String,
    val quest_answer3: String?,
    val quest_answer4: String?,
    val quest_explanation: String,
    val lvl_id: Byte,
    val theme_id: Byte,
)

@Entity
data class Level(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val lvl_id: Byte,
    val lvl_name: String,
)

@Entity
data class Theme(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val theme_id: Byte,
    val theme_name: String,
)

data class ServerResponse<T>(
    val code: Int,
    val message: String?,
    val data: T?
)

data class Params(
    val level: Level,
    val theme: Theme,
)

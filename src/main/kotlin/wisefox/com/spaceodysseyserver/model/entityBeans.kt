package wisefox.com.spaceodysseyserver.model

import javax.persistence.*

@Entity
@Table(name = "Question")
data class QuestionBean(
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
@Table(name = "Level")
data class LevelBean(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val lvl_id: Byte,
    val lvl_name: String,
)

@Entity
@Table(name = "Theme")
data class ThemeBean(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val theme_id: Byte,
    val theme_name: String,
)

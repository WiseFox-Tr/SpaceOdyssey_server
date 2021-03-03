package wisefox.com.spaceodysseyserver.model.dao


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import wisefox.com.spaceodysseyserver.model.Level
import wisefox.com.spaceodysseyserver.model.Question
import wisefox.com.spaceodysseyserver.model.Theme


@Repository
interface QuizDAO : JpaRepository<Question, Int> {
    @Query(value = "SELECT * FROM question WHERE lvl_id = ?1 AND theme_id = ?2", nativeQuery = true)
    fun findQuestionsByLevelIdAndThemeId(lvl_id : Byte, theme_id : Byte) : ArrayList<Question>
}

@Repository
interface LevelDAO : JpaRepository<Level, Byte>

@Repository
interface ThemeDAO : JpaRepository<Theme, Byte>

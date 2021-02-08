package wisefox.com.spaceodysseyserver.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import wisefox.com.spaceodysseyserver.model.*
import wisefox.com.spaceodysseyserver.model.QuizDAO
import wisefox.com.spaceodysseyserver.utils.traceServerRequest


@RestController
class QuizController(@Autowired val quizDAO: QuizDAO) {

    //waiting for implementation of the database
    var questionsToSend = ArrayList<QuestionBean>()

    //http://localhost:8080/testServer
    @GetMapping("/testServer")
    fun testServer(): String {
        println("/testServer")
        return "Server is ok"
    }

    /* Json recieve for getQuestions *************
    {
      "level": {
        "lvl_id": 1,
        "lvl_name": "Débutant"
      },
      "theme": {
        "theme_id": 1,
        "theme_name": "Systèmes planétaires"
      }
    }
    ******************************************* */
    //http://localhost:8080/getQuestions
    @PostMapping("getQuestions")
    fun getQuestions(@RequestBody params: Params): ResponseCode<List<QuestionBean>> {
        traceServerRequest("/getQuestions")
        print("Quiz param : $params")

        //todo: once the database will be up and running -> manage errors with a try catch bloc

        //get questionsToSend & return them to client
        val data = quizDAO.findQuestionsByLevelIdAndThemeId(params.level.lvl_id, params.theme.theme_id)

        return ResponseCode(200, "", data)
    }
}

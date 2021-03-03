package wisefox.com.spaceodysseyserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import wisefox.com.spaceodysseyserver.model.*
import wisefox.com.spaceodysseyserver.model.dao.LevelDAO
import wisefox.com.spaceodysseyserver.model.dao.QuizDAO
import wisefox.com.spaceodysseyserver.model.dao.ThemeDAO
import wisefox.com.spaceodysseyserver.utils.traceServerRequest


@RestController
class QuizController(val quizDAO: QuizDAO, val themeDAO: ThemeDAO, val levelDAO: LevelDAO) {

    //http://localhost:8080/testServer
    @GetMapping("/testServer")
    fun testServer(): String {
        println("/testServer")
        return "Server is ok"
    }

    //http://localhost:8080/getParams
    @GetMapping("/getParams")
    fun getParams(): ServerResponse<Params> {
        traceServerRequest("/getParams")
        return try {
            val paramsRetrieved = Params(levels = levelDAO.findAll(), themes = themeDAO.findAll())
            println("Params retrieved : $paramsRetrieved")
            ServerResponse(code = 200, message = "", paramsRetrieved)
        }
        catch (e: Exception) {
            e.printStackTrace()
            ServerResponse(code = ServerConst.ERR_PARAMS, message = e.message, data = null)
        }
    }

    /* Example of Json send by client for getQuestions *************
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
    /** url : http://localhost:8080/getQuestions */
    @PostMapping("getQuestions")
    fun getQuestions(@RequestBody params: Params): ServerResponse<List<Question>> {
        traceServerRequest("/getQuestions")
        print("Quiz param : $params")

        val questionsRetrieved: ArrayList<Question>
        val questionsToSend : List<Question>


        //tries to get all questions corresponding to params received
        //checks if there is at least 10 questions. Takes randomly 10 of them and return them to client
        //if a problem occurs, returns an error message
        try {
            questionsRetrieved = quizDAO.findQuestionsByLevelIdAndThemeId(params.levels[0].lvl_id, params.themes[0].theme_id)

            if(questionsRetrieved.size < ServerConst.NB_QUESTIONS)
                throw Exception("Not enough questions for these parameters")

            questionsToSend = questionsRetrieved.shuffled().take(ServerConst.NB_QUESTIONS)

            //control print
            println("\nNb questions to send to client : ${questionsToSend.size} --> List of these : ")
            questionsToSend.forEach { println("$it") }

            return ServerResponse(200, "", questionsToSend)
        }
        catch (e: Exception) {
            e.printStackTrace()
            return ServerResponse(code = ServerConst.ERR_QUEST, message = e.message, data = null)
        }
    }
}

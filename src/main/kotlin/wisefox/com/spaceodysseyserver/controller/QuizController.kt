package wisefox.com.spaceodysseyserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import wisefox.com.spaceodysseyserver.model.*
import wisefox.com.spaceodysseyserver.utils.traceServerRequest


@RestController
class QuizController(val quizDAO: QuizDAO) {

    //http://localhost:8080/testServer
    @GetMapping("/testServer")
    fun testServer(): String {
        println("/testServer")
        return "Server is ok"
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
    //http://localhost:8080/getQuestions
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
            questionsRetrieved = quizDAO.findQuestionsByLevelIdAndThemeId(params.level.lvl_id, params.theme.theme_id)

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
            return ServerResponse(ServerConst.ERR_QUEST, e.message)
        }
    }
}

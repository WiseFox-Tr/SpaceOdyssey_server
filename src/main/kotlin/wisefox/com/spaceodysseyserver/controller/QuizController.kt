package wisefox.com.spaceodysseyserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import wisefox.com.spaceodysseyserver.model.*
import wisefox.com.spaceodysseyserver.utils.traceServerRequest


@RestController
class QuizController {

    //waiting for implementation of the database
    //todo add data base & implement DAO for questions
    var questionsToSend = ArrayList<QuestionBean>()

    //http://localhost:8080/testServer
    @GetMapping("/testServer")
    fun testServer(): String {
        println("/testServer")
        return "Server is ok"
    }

    /* Json recieve for getQuestions *************
    {
      "levelBean": {
        "lvl_id": 1,
        "lvl_name": "Débutant"
      },
      "themeBean": {
        "theme_id": 1,
        "theme_name": "Systèmes planétaires"
      }
    }
    ******************************************* */
    //http://localhost:8080/getQuestions
    @PostMapping("getQuestions")
    fun getQuestions(@RequestBody params: ParamsBean): ResponseCodeBean<List<QuestionBean>> {
        traceServerRequest("/getQuestions")
        print("Quiz param : $params")

        //todo: once the database will be up and running -> manage errors with a try catch bloc

        //get questionsToSend & return them to client
        val data = addQuestions()
        return ResponseCodeBean(200,"",data)
    }



    /* ********only for test before database ******** */
    fun addQuestions(): List<QuestionBean> {
        //clear
        questionsToSend.clear()
        //first question good level & theme
        questionsToSend.add(QuestionBean(
            1,
            "Quelle est la première planète du système solaire ?",
            "Mercure",
            "Venus",
            "la Terre",
            "Mars",
            "Explication simple pour la première question",
            LevelBean(1, "Débutant"),
            ThemeBean(1, "Systèmes planétaires")
        ))
        //second question good level & theme
        questionsToSend.add(QuestionBean(
            2,
            "Quelle est la deuxième planète du système solaire ?",
            "Venus",
            "Mercure",
            "Mars",
            "Neptune",
            "Explication simple pour la deuxième question",
            LevelBean(1, "Débutant"),
            ThemeBean(1, "Systèmes planétaires")
        ))

        return questionsToSend
    }
}

package wisefox.com.spaceodysseyserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import wisefox.com.spaceodysseyserver.model.*


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




    /* Json recieve for getQuestion *************
    {
        "level" :
        {
            "lvl_id" : 1,
            "lvl_name": "Débutant"
        },
        "theme" :
        {
            "theme_id" : 1,
            "theme_name": "Systèmes solaires"
        }
    }
    ******************************************* */
    //http://localhost:8080/getQuestions
    @PostMapping("getQuestions")
    fun getQuestions(@RequestBody quizParamBean: QuizParamBean): ResponseCodeBean<List<QuestionBean>> {
        print(("/getQuestions"))
        print("Quiz param : $quizParamBean")

        //todo: once the database will be up and running -> manage errors with a try catch bloc

        //get questionsToSend & return them to client
        val data = addQuestions()
        return ResponseCodeBean(200,"",data)
    }



    /* ********only for test before database ******** */
    fun addQuestions(): List<QuestionBean> {
        questionsToSend.add(QuestionBean(
            1,
            "Quelle est la première planète du système solaire ?",
            "Mercure",
            "Venus",
            "la Terre",
            "Mars",
            "Explication simple",
            LevelBean(1, "Débutant"),
            ThemeBean(1, "Systèmes solaires")
        ))
        return questionsToSend
    }

}
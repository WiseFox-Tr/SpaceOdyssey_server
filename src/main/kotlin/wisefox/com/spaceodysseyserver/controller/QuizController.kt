package wisefox.com.spaceodysseyserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizController {

    //http://localhost:8080/testServer
    @GetMapping("/testServer")
    fun testServer(): String {
        println("/testServer")
        return "Server is ok"
    }
}
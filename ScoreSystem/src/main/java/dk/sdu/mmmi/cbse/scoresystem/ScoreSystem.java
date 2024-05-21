package dk.sdu.mmmi.cbse.scoresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

//RequestMapping sends a request to the localhost url port-number.
@SpringBootApplication
@RequestMapping("/pointscore")
@RestController
public class ScoreSystem {
    private Long totalScore = 0L;

    //This method starts the Spring-Boot Application to recieve inputs from the client.
    public static void main(String[] args) {
        SpringApplication.run(ScoreSystem.class, args);
    }

    //This method increments the value along the score in the Game-Class, and sends the value through HTTPRequest to the localhost.
    @PutMapping("/refresh/{pointscore}")
    public Long incrementScore(@PathVariable(value = "pointscore") Long pointscore) {
        totalScore += pointscore;
        return totalScore;
    }

    //This value returns the score on the localhost, that has been incremented.
    @GetMapping
    public Long getScore() {
        return totalScore;
    }
}
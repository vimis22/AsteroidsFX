package dk.sdu.mmmi.cbse.scoresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RequestMapping("/pointscore")
@RestController
public class ScoreSystem {
    private Long totalScore = 0L;

    public static void main(String[] args) {
        SpringApplication.run(ScoreSystem.class, args);
    }

    @PutMapping("/refresh/{pointscore}")
    public Long incrementScore(@PathVariable(value = "pointscore") Long pointscore) {
        totalScore += pointscore;
        return totalScore;
    }

    @GetMapping
    public Long getScore() {
        return totalScore;
    }
}
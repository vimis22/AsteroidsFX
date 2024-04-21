package dk.sdu.mmmi.cbse.main;
import javafx.application.Application;

import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main extends Application {
    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("C:\\Users\\vivek\\Downloads\\Projects\\AsteroidsFXNEW\\Core\\src\\ressources\\spring.xml");
        Game game = context.getBean("Game",Game.class);
    }
}

package dk.sdu.mmmi.cbse.main;
import javafx.application.Application;

import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main extends Application {
    /**
     * The game is started from here, upon pressing start.
     */
    public static void main(String[] args) {
        launch(Main.class);
    }

    /**
     * This method reads the Spring Container (HelperClass), and creates dependency injection through constructor in the Game Class.
     *
     * @param window    The window illustrates the display of the window.
     * @throws Exception    Throws exception, if error occurs.
     */
    @Override
    public void start(Stage window) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelperClass.class);

        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        Game game = context.getBean(Game.class);
        game.start(window);
        game.render();
    }

}

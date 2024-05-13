package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Game {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private Text totalScoreDisplay;
    private int pointScore;
    private final List<IEntityProcessingService> iEntityProcessingService;
    private final List<IPostEntityProcessingService> iPostEntityProcessingService;
    private final List<IGamePluginService> iGamePluginService;

    public Game(List<IEntityProcessingService> iEntityProcessingService, List<IPostEntityProcessingService> iPostEntityProcessingService, List<IGamePluginService> iGamePluginService) {
        this.iEntityProcessingService = iEntityProcessingService;
        this.iPostEntityProcessingService = iPostEntityProcessingService;
        this.iGamePluginService = iGamePluginService;
    }

    public void start(Stage window) throws Exception {
        pointScore = 0;
        totalScoreDisplay = new Text(10, 20, "Destroyed asteroids: " + pointScore);
        totalScoreDisplay.setFill(Color.BLUE);
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(totalScoreDisplay);


        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
            if (event.getCode().equals(KeyCode.DOWN)){
                gameData.getKeys().setKey(GameKeys.DOWN, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
            if (event.getCode().equals(KeyCode.DOWN)){
                gameData.getKeys().setKey(GameKeys.DOWN, false);
            }

        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygon.setFill(entity.getColor());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }


        render();

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();

    }

    public void render() {
        new AnimationTimer() {
            private final long then = 0;

            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        //Når et entity dør, så skal den fjernes fra world, polygons og gameWindow.
        //Her skal du tænke på, hvordan en entity skal fjernes.
        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            //polygon.setFill(entity.getColor());
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
            if(entity.getDeath()){
                polygons.remove(polygon);
                world.removeEntity(entity);
                gameWindow.getChildren().remove(polygon);

                if(entity.getClass().getSimpleName().contains("Asteroid")){
                    pointScore++;
                    totalScoreDisplay.setText("Destroyed asteroids: " + pointScore);
                }
            }
        }
    }

    public List<IGamePluginService> getPluginServices() {
        return iGamePluginService;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return iEntityProcessingService;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return iPostEntityProcessingService;
    }

}
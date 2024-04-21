package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;
import static javafx.application.Application.launch;

public class Game {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    public Game(List<? extends IGamePluginService> pluginServices, List<? extends IEntityProcessingService> entityProcessingServices, List<? extends IPostEntityProcessingService> postEntityProcessingServices) {

    }

    public static void main(String[] args) {
        launch(Main.class);
    }

    public void start(Stage window) throws Exception {
        loadPlugins();

        Scene scene = new Scene(gameWindow,600,600);
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
        render();

    }

    private void loadPlugins(){
        for (IGamePluginService pluginService : getPluginServices()){
            pluginService.start(gameData,world);
        }
    }

    private void render() {
        new AnimationTimer() {
            private long then = 0;

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
            }
        }

    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}

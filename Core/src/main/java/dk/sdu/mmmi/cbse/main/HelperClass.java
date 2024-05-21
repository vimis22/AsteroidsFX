package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class HelperClass {
    public HelperClass(){

    }

    @Bean
    public Game game(){
        return new Game(getEntityProcessingServices(), getPostEntityProcessingServices(), getPluginServices());
    }

    /**
     * This method loads the IGamePluginService, in order to fetch other components that use this interfaces.
     * @return {@IGamePluginService}    Gets the IGamePluginService
     */
    @Bean
    public static List<IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    /**
     * This method loads the IEntityProcessingService, in order to fetch other components that use this interfaces.
     * @return {@IEntityProcessingService}    Gets the IEntityProcessingService
     */
    @Bean
    public static List<IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    /**
     * This method loads the IPostEntityProcessingService, in order to fetch other components that use this interfaces.
     * @return {@IPostEntityProcessingService}    Gets the IPostEntityProcessingService
     */
    @Bean
    public static List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}

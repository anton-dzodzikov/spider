package solutions.lightprocessing.spider.api.configuration

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration
import solutions.lightprocessing.spider.api.DataController
import solutions.lightprocessing.spider.api.EntityController

@Configuration
class JerseyConfiguration extends ResourceConfig {
    JerseyConfiguration() {
        register(DataController.class)
        register(EntityController.class)

        register(ExceptionHandler.class)
    }
}

package solutions.lightprocessing.spider.api.configuration

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration
import solutions.lightprocessing.spider.api.DataController

@Configuration
class JerseyConfiguration extends ResourceConfig {
    JerseyConfiguration() {
        register(DataController.class)
    }
}

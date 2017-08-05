package solutions.lightprocessing.spider.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import solutions.lightprocessing.spider.services.EntityLocator

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Controller
@Path('api/entities')
class EntityController {
    @Autowired
    EntityLocator entityLocator

    @GET
    @Path('/')
    @Produces(MediaType.APPLICATION_JSON)
    Set<String> getAvailableEntities() {
        entityLocator.getAvailableEntities()
    }
}

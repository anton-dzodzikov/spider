package solutions.lightprocessing.spider.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import solutions.lightprocessing.spider.services.DataService

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Controller
@Path('api/data')
class DataController {
    @Autowired
    DataService dataService

    @GET
    @Path('{entityName}')
    @Produces(MediaType.APPLICATION_JSON)
    List<Map> getAll(@PathParam('entityName') String name) {
        dataService.getAll(name)
    }
}

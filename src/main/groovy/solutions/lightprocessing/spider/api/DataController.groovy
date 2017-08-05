package solutions.lightprocessing.spider.api

import org.springframework.stereotype.Controller

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Controller
@Path('api/data')
class DataController {
    @GET
    @Path('{entityName}')
    @Produces(MediaType.APPLICATION_JSON)
    List<Map> getAll(@PathParam('entityName') String name) {
        [[:]]
    }
}

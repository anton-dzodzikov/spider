package solutions.lightprocessing.spider.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import solutions.lightprocessing.spider.domain.Filter
import solutions.lightprocessing.spider.services.DataService

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Controller
@Path('api/data')
class DataController {
    @Autowired
    DataService dataService

    @GET
    @Path('{entityName}')
    @Produces(MediaType.APPLICATION_JSON)
    List<Map> getAll(@PathParam('entityName') String name, @QueryParam('filters') String filters) {
        dataService.getAll(name, filters == null ? [] : parseFilters(filters))
    }

    List<Filter> parseFilters(String serializedFilters) {
        new ObjectMapper().with { mapper ->
            typeFactory.constructCollectionType(List.class, Filter.class)
                    .with { mapper.readValue(serializedFilters, it) as List<Filter> }

        }
    }
}

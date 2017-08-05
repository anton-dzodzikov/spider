package solutions.lightprocessing.spider.api.configuration

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

import static javax.ws.rs.core.MediaType.APPLICATION_JSON
import static javax.ws.rs.core.Response.Status.BAD_REQUEST

class ExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    Response toResponse(Exception e) {
        Response.status(BAD_REQUEST)
                .entity("${e.class.simpleName}: ${e.message}".toString())
                .type(APPLICATION_JSON)
                .build()
    }
}
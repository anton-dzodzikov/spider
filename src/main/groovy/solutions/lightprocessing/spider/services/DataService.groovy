package solutions.lightprocessing.spider.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import solutions.lightprocessing.spider.components.EntityNameParser

@Service
class DataService {
    @Autowired
    JdbcTemplate jdbcTemplate

    @Autowired
    EntityNameParser nameParser

    // TODO: First we should check if table for such entity exists (via tables API)
    List<Map> getAll(String entityName) {
        nameParser.getTableName(entityName)
                .with { "select * from ${it}" }
                .with { jdbcTemplate.queryForList(it) }
    }
}

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

    @Autowired
    EntityLocator entityLocator

    List<Map> getAll(String entityName) {
        if (!entityLocator.availableEntities.contains(entityName)) {
            throw new IllegalArgumentException("No such exposed entity: \"${entityName}\"")
        }

        nameParser.getTableName(entityName)
                .with { "select * from ${it}" }
                .with { jdbcTemplate.queryForList(it) }
    }
}

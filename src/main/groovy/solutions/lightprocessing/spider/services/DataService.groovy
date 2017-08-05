package solutions.lightprocessing.spider.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import solutions.lightprocessing.spider.components.EntityNameParser
import solutions.lightprocessing.spider.domain.Filter

@Service
class DataService {
    @Autowired
    JdbcTemplate jdbcTemplate

    @Autowired
    EntityNameParser nameParser

    @Autowired
    EntityLocator entityLocator

    List<Map> getAll(String entityName, List<Filter> filters) {
        if (!entityLocator.availableEntities.contains(entityName)) {
            throw new IllegalArgumentException("No such exposed entity: \"${entityName}\"")
        }

        nameParser.getTableName(entityName)
                .with { "select * from ${it}" }
                .with { jdbcTemplate.queryForList(it) }
    }
}

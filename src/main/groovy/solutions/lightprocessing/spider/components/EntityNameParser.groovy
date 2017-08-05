package solutions.lightprocessing.spider.components

import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EntityNameParser {
    @Value('${spider.prefix:SPIDER}')
    String prefix

    String getTableName(String entityName) {
        StringUtils.splitByCharacterTypeCamelCase(entityName)
                .with { it.toList() }
                .collect { it.toUpperCase() }
                .with { it.plus(0, prefix) }
                .join('_')
    }
}

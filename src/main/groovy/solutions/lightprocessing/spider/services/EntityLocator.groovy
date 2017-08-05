package solutions.lightprocessing.spider.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.support.DatabaseMetaDataCallback
import org.springframework.jdbc.support.JdbcUtils
import org.springframework.jdbc.support.MetaDataAccessException
import org.springframework.stereotype.Service
import solutions.lightprocessing.spider.components.EntityNameParser

import javax.annotation.PostConstruct
import javax.sql.DataSource
import java.sql.DatabaseMetaData
import java.sql.SQLException

@Service
class EntityLocator {
    @Value('${spider.prefix:SPIDER}')
    String prefix

    @Autowired
    EntityNameParser nameParser

    @Autowired
    DataSource dataSource

    Map<String, String> entityToTableMapping = [:]

    @PostConstruct
    void init() {
        entityToTableMapping = getTables()
                .findAll { it.startsWith(prefix.toUpperCase()) }
                .collectEntries { [(nameParser.getEntityNameByTableName(it)): it] }
    }

    Set<String> getAvailableEntities() {
        entityToTableMapping.keySet()
    }

    List<String> getTables() {
        List<String> tables = []

        JdbcUtils.extractDatabaseMetaData(dataSource, new DatabaseMetaDataCallback() {
            @Override
            Object processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
                dbmd.getTables(dbmd.getUserName(), null, null, 'TABLE').with {
                    while(it.next()) {
                        tables << it.getString(3).toUpperCase()
                    }
                }
            }
        })

        tables
    }
}

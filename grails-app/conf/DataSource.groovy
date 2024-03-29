dataSource {
    dbCreate = "create-drop" // one of 'create', 'create-drop','update'
    url = "jdbc:h2:mem:devDB"
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb"
            pooled = true
            driverClassName = "org.h2.Driver"
            username = "sa"
            password = ""
        }
    }
    production {
        dataSource {
			dbCreate = "update"
			pooled = true
			url = "jdbc:mysql://localhost:3306/folksonomy?zeroDateTimeBehavior=convertToNull"
			username ="folksonomy"
			password ="folksonomy"
			dialect= org.hibernate.dialect.MySQLDialect
            driverClassName = "com.mysql.jdbc.Driver"
        }
    }
}

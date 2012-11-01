dataSource {
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
//            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
//            url = "jdbc:h2:mem:devDB"
			pooled = true
			dbCreate = "update" // one of 'create', 'create-drop','update'
//			url = "jdbc:mysql://localhost/jcharmonypos"
			url = "jdbc:mysql://127.0.0.1/jcharmonypos"
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "root"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:hsqldb:mem:testDb"
        }
    }
    production {
        dataSource {
//            dbCreate = "update"
//            url = "jdbc:hsqldb:file:prodDb;shutdown=true"
			dbCreate = "update" // one of 'create', 'create-drop','update'
//			url = "jdbc:mysql://localhost/jcharmonypos"
			url = "jdbc:mysql://jcharmonypos-db/jcharmonypos"
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "root"
        }
    }
}

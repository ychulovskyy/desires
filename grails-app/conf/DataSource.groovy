dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        grails {
            mongo {
                host = "127.0.0.1"
                port = 27017
                username = ""
                password = ""
                databaseName = "desires"
            }
        }
    }
    test {
        grails {
            mongo {
                host = "localhost"
                port = 27017
                username = ""
                password = ""
                databaseName = "desires"
            }
        }
    }
    production {
        grails {
            mongo {
                host = "localhost"
                port = 27017
                username = ""
                password = ""
                databaseName = "desires"
            }
        }
    }
}

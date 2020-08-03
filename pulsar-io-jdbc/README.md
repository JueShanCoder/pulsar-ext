# Pulsar IO JDBC

## Sink

``` sh
$ ./bin/pulsar-admin sinks localrun --name local-jdbc-sink --retain-ordering --processing-guarantees EFFECTIVELY_ONCE --parallelism 1 --tenant public --namespace practices --inputs public/practices/local-jdbc --archive connectors/pulsar-io-jdbc-1.0.0-SNAPSHOT.nar --sink-config '{"driver":"com.mysql.cj.jdbc.Driver","jdbcUrl":"jdbc:mysql://mysql:3306","username":"","password":""}'
```

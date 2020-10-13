# Pulsar IO Binlog


# TODOs

- [ ] 持久化BinlogOffset
  - [ ] 事务提交/回滚操作判断
- [ ] 如何判断一个Query事件是DDL事件
  - [x] 过滤`BEGIN`和`COMMIT`语句
- [ ] ~~Dml事件没有列名信息，如何保证脱敏函数的兼容性~~


## Source

``` sh
$ ./bin/pulsar-admin sources localrun --name local-binlog-source --parallelism 1 --tenant public --namespace practices --destination-topic-name public/practices/local-mysql-binlog --archive connectors/pulsar-io-binlog-1.0.0-SNAPSHOT.nar --source-config '{"hostname":"mysqlsrc","port":3306,"username":"","password":""}'
```

## Sink

``` sh
$ ./bin/pulsar-admin sinks localrun --name local-binlog-sink --retain-ordering --processing-guarantees EFFECTIVELY_ONCE --parallelism 1 --tenant public --namespace practices --inputs public/practices/local-mysql-binlog --archive connectors/pulsar-io-binlog-1.0.0-SNAPSHOT.nar --sink-config '{"hostname":"mysqldst","port":3306,"username":"","password":""}'
```

## Producer

``` sh
$ ./bin/pulsar-client produce -m '{"header": {}}' public/practices/local-mysql-binlog
```

## Consumer

``` sh
$ ./bin/pulsar-client consume -s pulsar-client-cli -n 0 -t Failover public/practices/local-mysql-binlog
```

## DataGen

``` sh
$ ./bin/pulsar-admin sources localrun --name local-datagen-source --tenant public --namespace practices --destination-topic-name public/practices/local-datagen --archive connectors/pulsar-io-data-generator-2.6.1.nar
```

``` sh
$ ./bin/pulsar-admin sinks localrun --name local-datagen-sink --tenant public --namespace practices --inputs public/practices/local-datagen --archive connectors/pulsar-io-data-generator-2.6.1.nar
```

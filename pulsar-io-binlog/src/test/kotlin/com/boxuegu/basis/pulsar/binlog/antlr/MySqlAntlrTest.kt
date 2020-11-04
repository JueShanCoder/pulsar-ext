package com.boxuegu.basis.pulsar.binlog.antlr

import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ConsoleErrorListener
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.testng.annotations.Test

class MySqlAntlrTest {
    @Test
    fun parseDdl() {
        // createDatabase alterDatabase dropDatabase
        // createTable alterTable dropTable
        // renameTable truncateTable
        val cases = """
-- dropDatabase
DROP DATABASE IF EXISTS `bxg-edu`;
-- createDatabase
CREATE DATABASE `bxg-edu` CHARACTER SET utf8 COLLATE utf8_general_ci;
-- alterDatabase
ALTER DATABASE `bxg-edu` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
-- dropTable
DROP TABLE IF EXISTS `bxg-edu`.`users`;
-- createTable
CREATE TABLE `bxg-edu`.`users` ( `id` BIGINT ( 20 ) NOT NULL, `name` VARCHAR ( 64 ) NOT NULL, PRIMARY KEY ( `id` ) ) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;
-- alterTable
ALTER TABLE `bxg-edu`.`users` MODIFY COLUMN `name` VARCHAR ( 128 ) NOT NULL AFTER `id`;
-- renameTable
RENAME TABLE `bxg-edu`.`users` TO `bxg-edu`.`roles`;
-- truncateTable
TRUNCATE TABLE `bxg-edu`.`roles`;
-- dropTable
DROP TABLE `bxg-edu`.`roles`;
-- dropDatabase
DROP DATABASE `bxg-edu`;
        """.trimIndent()
        val input = SqlCharStream.fromString(cases)
        val lexer = MySqlLexer(input)
        val parser = MySqlParser(CommonTokenStream(lexer))
        val listener = Slf4jParserListener(parser)
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE)
        parser.addErrorListener(Slf4jErrorListener())
        ParseTreeWalker.DEFAULT.walk(listener, parser.root())
    }
}

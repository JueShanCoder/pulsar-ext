package com.boxuegu.basis.pulsar.jdbc

import java.sql.Connection
import java.sql.DriverManager

class JdbcUtilsH2Tests : JdbcUtilsTests() {
    override fun setup(): Connection {
        connection = DriverManager.getConnection("jdbc:h2:mem:test")
        connection.prepareStatement(
            """
            CREATE TABLE IF NOT EXISTS "samples"
            (
                "id"              BIGINT,
                "f_boolean"       BOOLEAN,
                "f_bit"           BIT(1),
                "f_tinyint"       TINYINT,
                "f_smallint"      SMALLINT,
                "f_integer"       INTEGER,
                "f_bigint"        BIGINT,
                "f_float"         FLOAT,
                "f_real"          REAL,
                "f_double"        DOUBLE,
                "f_numeric"       NUMERIC,
                "f_decimal"       DECIMAL,
                "f_char"          CHAR,
                "f_varchar"       VARCHAR,
                "f_longvarchar"   LONGVARCHAR,
                "f_binary"        BINARY,
                "f_varbinary"     VARBINARY,
                "f_longvarbinary" LONGVARBINARY,
                "f_date"          DATE,
                "f_time"          TIME,
                "f_datetime"      DATETIME,
                "f_timestamp"     TIMESTAMP,
                CONSTRAINT SAMPLES_PK PRIMARY KEY ("id")
            );
        """.trimIndent()
        ).execute()
        return connection
    }
}

package com.boxuegu.basis.pulsar.binlog.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jErrorListener extends BaseErrorListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jParserListener.class);

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object symbol, int l, int p, String msg, RecognitionException ex) {
        LOGGER.error("line " + l + ":" + p + " " + msg, ex);
    }
}

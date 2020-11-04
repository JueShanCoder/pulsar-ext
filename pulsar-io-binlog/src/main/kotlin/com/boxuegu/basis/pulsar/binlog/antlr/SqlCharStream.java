// https://github.com/antlr/antlr4/blob/master/doc/resources/CaseChangingCharStream.java
package com.boxuegu.basis.pulsar.binlog.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.misc.Interval;

/**
 * This class supports case-insensitive lexing by wrapping an existing
 * {@link CharStream} and forcing the lexer to see either upper or
 * lowercase characters. Grammar literals should then be either upper or
 * lower case such as 'BEGIN' or 'begin'. The text of the character
 * stream is unaffected. Example: input 'BeGiN' would match lexer rule
 * 'BEGIN' if constructor parameter upper=true but getText() would return
 * 'BeGiN'.
 */
public class SqlCharStream implements CharStream {

    public static SqlCharStream fromString(String source) {
        return new SqlCharStream(CharStreams.fromString(source), true);
    }

    public static SqlCharStream fromString(String source, String name) {
        return new SqlCharStream(CharStreams.fromString(source, name), true);
    }

    public static SqlCharStream fromString(String source, String name, boolean upper) {
        return new SqlCharStream(CharStreams.fromString(source, name), upper);
    }

    final CharStream stream;
    final boolean upper;

    /**
     * Constructs a new CaseChangingCharStream wrapping the given {@link CharStream} forcing
     * all characters to upper case or lower case.
     *
     * @param stream The stream to wrap.
     * @param upper  If true force each symbol to upper case, otherwise force to lower.
     */
    public SqlCharStream(CharStream stream, boolean upper) {
        this.stream = stream;
        this.upper = upper;
    }

    @Override
    public String getText(Interval interval) {
        return stream.getText(interval);
    }

    @Override
    public void consume() {
        stream.consume();
    }

    @Override
    public int LA(int i) {
        int c = stream.LA(i);
        if (c <= 0) {
            return c;
        }
        if (upper) {
            return Character.toUpperCase(c);
        }
        return Character.toLowerCase(c);
    }

    @Override
    public int mark() {
        return stream.mark();
    }

    @Override
    public void release(int marker) {
        stream.release(marker);
    }

    @Override
    public int index() {
        return stream.index();
    }

    @Override
    public void seek(int index) {
        stream.seek(index);
    }

    @Override
    public int size() {
        return stream.size();
    }

    @Override
    public String getSourceName() {
        return stream.getSourceName();
    }
}

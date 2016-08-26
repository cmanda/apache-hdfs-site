/**
 * 
 */
package com.axway.ssc.st.plugins.site.hdfs;

import java.io.IOException;
import java.io.InputStream;

public class AlwaysOpenOutputStream extends InputStream {

	private final InputStream stream;

    public AlwaysOpenOutputStream(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public void close() {
        //No closing for this stream - to avoid AWS errors
    }

    @Override
    public int read() throws IOException {
        return stream.read();
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return stream.read(bytes);
    }

    @Override
    public int read(byte[] bytes, int i, int i1) throws IOException {
        return stream.read(bytes, i, i1);
    }

    @Override
    public long skip(long l) throws IOException {
        return stream.skip(l);
    }

    @Override
    public int available() throws IOException {
        return stream.available();
    }

    @Override
    public synchronized void mark(int i) {
        stream.mark(i);
    }

    @Override
    public synchronized void reset() throws IOException {
        stream.reset();
    }

    @Override
    public boolean markSupported() {
        return stream.markSupported();
    }

}

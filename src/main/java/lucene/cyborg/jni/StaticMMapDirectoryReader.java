package lucene.cyborg.jni;

import java.io.Closeable;
import java.io.IOException;

public class StaticMMapDirectoryReader implements Closeable {
    public static final long NULL_POINTER = 0;

    private StaticMMapDirectoryReader(long cyborgIndexReaderPointer) {
        this.cyborgIndexReaderPointer = cyborgIndexReaderPointer;
    }

    public static StaticMMapDirectoryReader open(String path) {
        long cyborgIndexReaderPointer = openMmapDirectoryReader(path);
        if (cyborgIndexReaderPointer != NULL_POINTER) {
            return new StaticMMapDirectoryReader(cyborgIndexReaderPointer);
        } else {
            // TODO : throw
            return null;
        }
    }

    private static native long openMmapDirectoryReader(String path);

    private static native void closeMmapDirectoryReader(long pointer);

    public long getCyborgIndexReaderPointer() {
        return cyborgIndexReaderPointer;
    }

    private long cyborgIndexReaderPointer = NULL_POINTER;

    @Override
    public void close() throws IOException {
        if (cyborgIndexReaderPointer != NULL_POINTER) {
            try {
                closeMmapDirectoryReader(cyborgIndexReaderPointer);
            } finally {
                cyborgIndexReaderPointer = NULL_POINTER;
            }
        }
    }
}

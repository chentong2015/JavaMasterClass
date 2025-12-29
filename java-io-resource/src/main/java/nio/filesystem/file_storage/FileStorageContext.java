package nio.filesystem.file_storage;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FileStorageContext {

    private static final Map<FileStorageContext, String> contextMap = new ConcurrentHashMap();
    private final boolean cleanUpOnClose;

    public FileStorageContext(boolean cleanUpOnClose) {
        this.cleanUpOnClose = cleanUpOnClose;
        contextMap.putIfAbsent(this, "");
    }

    public void close() {
        this.cleanup();
        contextMap.remove(this);
    }

    protected boolean isCleanUpOnClose() {
        return this.cleanUpOnClose;
    }

    protected abstract void cleanup();

    public abstract URI getURI();

    static {
        try {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                for(FileStorageContext context : contextMap.keySet()) {
                    if (context.cleanUpOnClose) {
                        try {
                            context.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }}
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

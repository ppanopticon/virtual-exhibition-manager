package ch.unibas.dmi.dbis.vrem.server.handlers.content;

import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class RequestContentHandler implements Route {


    private final Path root;

    /**
     *
     * @param root
     */
    public RequestContentHandler(Path root) {
        this.root = root;
    }



    @Override
    public Object handle(Request request, Response response) throws Exception {

        Map<String, String> params = request.params();

        final String path = params.get(":path");

        if (path == null){
            response.status(404);
            return 404;
        }

        final Path absolute = this.root.resolve(path);
        if (!Files.exists(absolute)) {
            response.status(404);
            return 404;
        }

        /* Prepare response. */
        response.type(Files.probeContentType(absolute));
        response.header("Transfer-Encoding", "identity");
        /* Transfer data. */
        try (final InputStream in =  Files.newInputStream(absolute, StandardOpenOption.READ);
             final OutputStream out = response.raw().getOutputStream()) {
            int length = fastCopy(in, out);
            response.header("Content-Length", String.valueOf(length));
            out.flush();
        }

        return null;
    }

    /**
     *
     * @param src
     * @param dest
     * @throws IOException
     */
    public static int fastCopy(final InputStream src, final OutputStream dest) throws IOException {
        byte[] buffer = new byte[1024]; // Adjust if you want
        int bytesRead = 0;
        int totalBytesRead = 0;
        while ((bytesRead = src.read(buffer)) != -1)
        {
            dest.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;
        }
        return totalBytesRead;
    }
}

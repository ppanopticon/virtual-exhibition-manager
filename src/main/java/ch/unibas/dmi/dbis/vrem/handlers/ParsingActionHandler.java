package ch.unibas.dmi.dbis.vrem.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public abstract class ParsingActionHandler<A> implements ActionHandler<A> {

    /**
     * Invoked when an incoming request is routed towards this class by Java Spark. The method handles
     * that request, extracts named parameters and parses the (optional) request body using Jackson. The
     * resulting context object is then forwarded to the doGet() method.
     *
     * @param request  The request object providing information about the HTTP request
     * @param response The response object providing functionality for modifying the response
     * @return The content to be set in the response
     * @throws Exception implementation can choose to throw exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Map<String, String> params = request.params();
        if (params == null) {
            params = new HashMap<>();
        }
        response.type("application/json");

        GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        switch (request.requestMethod()) {
            case "GET":
                return gson.toJson(this.doGet(params));
            case "DELETE":
                this.doDelete(params);
                return null;
            case "POST":
                return gson.toJson(this.doPost(gson.fromJson(request.body(), this.inClass()), params));
            case "PUT":
                return gson.toJson(this.doPut(gson.fromJson(request.body(), this.inClass()), params));
            default:
                throw new MethodNotSupportedException(request);
        }
    }
}


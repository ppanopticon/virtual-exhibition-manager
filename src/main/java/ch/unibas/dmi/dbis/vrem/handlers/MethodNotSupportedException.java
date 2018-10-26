package ch.unibas.dmi.dbis.vrem.handlers;

import spark.Request;

public class MethodNotSupportedException extends ActionHandlerException {
    private static final long serialVersionUID = 4872163795505943837L;

    public MethodNotSupportedException(String message) {
        super(message);
    }

    public MethodNotSupportedException(Request request) {
        this("HTTP method '" + request.requestMethod() + "' is not supported for call to' " + request.url() + "'.");
    }
}

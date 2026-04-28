package spp.ui.exception;


/**
 * When a GUI cannot be displayed
 */

public class ViewLoadException extends RuntimeException {
    public ViewLoadException(String viewName, Throwable cause) {
        super("Could not load view: " + viewName + cause);
    }
}

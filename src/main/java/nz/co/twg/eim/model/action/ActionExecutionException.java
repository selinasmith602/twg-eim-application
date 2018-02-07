package nz.co.twg.eim.model.action;

import java.util.List;

public class ActionExecutionException extends Exception {

    private List<Throwable> causes;
    public ActionExecutionException() {
    }

    public ActionExecutionException(String message) {
        super(message);
    }

    public ActionExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionExecutionException(String message, List<Throwable> causes) {
        super(message);
        this.causes = causes;
    }

    public List<Throwable> getCauses() {
        return causes;
    }

    public ActionExecutionException(Throwable cause) {
        super(cause);
    }

    public ActionExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}

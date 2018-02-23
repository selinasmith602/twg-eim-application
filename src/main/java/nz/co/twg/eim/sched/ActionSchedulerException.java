package nz.co.twg.eim.sched;

public class ActionSchedulerException extends Exception {
    public ActionSchedulerException() {
    }

    public ActionSchedulerException(String message) {
        super(message);
    }

    public ActionSchedulerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionSchedulerException(Throwable cause) {
        super(cause);
    }

    public ActionSchedulerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

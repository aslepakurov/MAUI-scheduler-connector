package ua.kpi.comsys.maui.domain;

/**
 * Created by insane on 5/23/14.
 */
public enum ClassID {

    CANCEL_JOB_REQUEST(1, "canceljob"),
    CANCEL_JOB_RESPONSE(2, "mauiresponse"),

    CANCEL_SYS_RESERVATION_REQUEST(3, "cancelres"),
    CANCEL_SYS_RESERVATION_RESPONSE(4, "mauiresponse"),

    CHECK_JOB_REQUEST(5, "checkjob"),
    CHECK_JOB_RESPONSE(6, "jobupdate"),

    CHECK_NODE_REQUEST(7, "checknode"),
    CHECK_NODE_RESPONSE(8, "nodeupdate"),

    CHECK_RESERVATION_REQUEST(9, "checkres"),
    CHECK_RESERVATION_RESPONSE(10, "resupdate"),

    HOLD_JOB_REQUEST(11, "holdjob"),
    HOLD_JOB_RESPONSE(12, "mauiresponse"),

    MAUI_CONTROL_REQUEST(13, "mauicontrol"),
    MAUI_CONTROL_RESPONSE(14, "mauiresponse"),

    SUBMIT_JOB_REQUEST(15, "mauisubmit"),
    SUBMIT_JOB_RESPONSE(16, "mauiresponse"),

    RELEASE_JOB_REQUEST(17, "releasejob"),
    RELEASE_JOB_RESPONSE(18, "mauiresponse"),

    SET_SYS_RESERVATION_REQUEST(19, "setres"),
    SET_SYS_RESERVATION_RESPONSE(20, "mauiresponse"),

    QUEUE_STATE_REQUEST(21, "showq"),
    QUEUE_STATE_RESPONSE(22, "queue"),;

    private final int id;
    private final String command;

    private ClassID(int id, String command) {
        this.id = id;
        this.command = command;
    }

    public int getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }
}

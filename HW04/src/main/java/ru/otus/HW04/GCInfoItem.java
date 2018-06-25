package ru.otus.HW04;

class GCInfoItem {
    private String action;
    private String name;
    private String cause;
    private Long duration;

    public GCInfoItem(String action, String name, String cause, Long duration) {
        this.action = action;
        this.name = name;
        this.cause = cause;
        this.duration = duration;
    }

    public String getAction() {
        return action;
    }

    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }

    public Long getDuration() {
        return duration;
    }

}
package com.shailendra.enums;

public enum CallEvents{
    ORIGINATE("ORIGINATE"),
    RING("RING"),
    ANSWERED("ANSWERED"),
    HANGUP("HANGUP");

    private String value;

    CallEvents(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

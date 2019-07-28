package com.ltd.iuser.enums;

public enum Permission {
    CREATE(1), DELETE(2), UPDATE(4), RETRIEVE(8);

    private int value;

    Permission(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

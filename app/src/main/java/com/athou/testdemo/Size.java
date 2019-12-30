package com.athou.testdemo;

/**
 * @author yunshan
 * @date 2019-12-29
 */
public enum Size {
    Small(0.8f),
    Mid(1f),
    Large(1.2f);

    private float ractor = 1f;

    Size(float ractor) {
        this.ractor = ractor;
    }

    public float getRactor() {
        return ractor;
    }
}

package com.example.gcardi.autotest;

public class TestState {

    /*
    *   1 - red
    *   2 - green
    */
    private int[] stateAnswers = new int[10];

    public TestState() {
    }

    public int[] getStateAnswers() {
        return stateAnswers;
    }

    public void setStateRed(int index) {
        stateAnswers[index] = 1;
    }

    public void setStateGreen(int index) {
        stateAnswers[index] = 2;
    }

    public int getState(int index) {
        return stateAnswers[index];
    }
}

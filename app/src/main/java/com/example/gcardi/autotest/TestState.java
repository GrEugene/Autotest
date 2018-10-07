package com.example.gcardi.autotest;

import android.os.Parcel;
import android.os.Parcelable;

public class TestState implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(stateAnswers);
    }

    public static final Parcelable.Creator<TestState> CREATOR
            = new Parcelable.Creator<TestState>() {
        public TestState createFromParcel(Parcel in) {
            return new TestState(in);
        }

        public TestState[] newArray(int size) {
            return new TestState[size];
        }
    };

    private TestState(Parcel in) {
        in.readIntArray(stateAnswers);
    }
}

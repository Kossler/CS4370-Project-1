package uga.cs4370.mydb.impl;

import java.util.List;
import uga.cs4370.mydb.*;

public class DoubleGreaterThan implements Predicate {
    private int index;
    private double value;

    public DoubleGreaterThan(int index, double value) {
        this.index = index;
        this.value = value;
    }

    /**
     * Checks a row for a condition and returns true
     * if the row passes the predicate.
     */
    @Override
    public boolean check(List<Cell> row) {
        if (row.get(index).getAsDouble() > value) {
            return true;
        } else {
            return false;
        }
    }
}

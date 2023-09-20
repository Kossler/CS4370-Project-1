package uga.cs4370.mydb.impl;

import java.util.List;
import uga.cs4370.mydb.*;

public class IntegerLessThan implements Predicate {
    private int index;
    private int value;

    public IntegerLessThan(int index, int value) {
        this.index = index;
        this.value = value;
    }

    /**
     * Checks a row for a condition and returns true
     * if the row passes the predicate.
     */
    @Override
    public boolean check(List<Cell> row) {
        if (row.get(index).getAsInt() < value) {
            return true;
        } else {
            return false;
        }
    }
}

package uga.cs4370.mydb.impl;

import java.util.List;
import uga.cs4370.mydb.*;

public class StringEqual implements Predicate {
    private int index;
    private String value;

    public StringEqual(int index, String value) {
        this.index = index;
        this.value = value;
    }

    /**
     * Checks a row for a condition and returns true
     * if the row passes the predicate.
     */
    @Override
    public boolean check(List<Cell> row) {
        if (row.get(index).getAsString().equals(value)) {
            return true;
        } else {
            return false;
        }
    }
}

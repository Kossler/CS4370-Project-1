package uga.cs4370.mydb.impl;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.Cell;
import java.util.List;

public class PredicateImpl implements Predicate {
  
  List<Cell> row;
  
  public PredicateImpl(List<Cell> row) {
    this.row = row;
  }

  public boolean check(List<Cell> row) {
    boolean predicate = false;
    return predicate;
  }

}

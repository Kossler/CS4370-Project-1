package uga.cs4370.mydb.impl;


import java.util.ArrayList;
import java.util.List;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.RA;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        List<String> attr = new ArrayList<String>();
        attr.add("Name");
        attr.add("Age");
        attr.add("Instructor");

        List<Type> type = new ArrayList<Type>();
        type.add(Type.STRING);
        type.add(Type.INTEGER);
        type.add(Type.STRING);

       Cell row1cell1 = new Cell("John");
       Cell row1cell2 = new Cell(25);
       Cell row1cell3 = new Cell("Mr.Brown");
       
       Cell row2cell1 = new Cell("Sam");
       Cell row2cell2 = new Cell(38);
       Cell row2cell3 = new Cell("Mr.Adams");

       Cell row3cell1 = new Cell("Ally");
       Cell row3cell2 = new Cell(55);
       Cell row3cell3 = new Cell("Mr.Smith");

       List<List<Cell>> list1 = List.of(
                List.of(row1cell1 ,row1cell2, row1cell3), List.of(row2cell1 ,row2cell2, row2cell3), List.of(row3cell1 ,row3cell2, row3cell3)
        );
        // creates first table


        List<String> attr2 = new ArrayList<String>();
        attr.add("Name");
        attr.add("Age");
        attr.add("Salary");
        attr.add("Department");



        List<Type> type2 = new ArrayList<Type>();
        type.add(Type.STRING);
        type.add(Type.INTEGER);
        type.add(Type.INTEGER);
        type.add(Type.STRING);


        Cell trow1cell1 = new Cell("Mr.Brown");
        Cell trow1cell2 = new Cell(1);
        Cell trow1cell3 = new Cell(100000);
        Cell trow1cell4 = new Cell("Physics");
        
        Cell trow2cell1 = new Cell("Sam");
        Cell trow2cell2 = new Cell(2);
        Cell trow2cell3 = new Cell(200000);
        Cell trow2cell4 = new Cell("Bio");

        Cell trow3cell1 = new Cell("Ally");
        Cell trow3cell2 = new Cell(3);
        Cell trow3cell3 = new Cell(300000);
        Cell trow3cell4 = new Cell("CS");

        List<List<Cell>> list2 = List.of(
                 List.of(trow1cell1 ,trow1cell2, trow1cell3, trow1cell4), List.of(trow2cell1 ,trow2cell2, trow2cell3, trow2cell4), List.of(trow3cell1 ,trow3cell2, trow3cell3, trow3cell4)
         );
         // creates second table


        Relation table = new RelationImpl("Students", attr, type, list1);
        Relation table2 = new RelationImpl("Instructors", attr2, type2, list2);

        RA tester = new RAImpl();

        System.out.println("");
        System.out.println("Start CP");
        tester.cartesianProduct(table, table2).print();
        System.out.println("End CP");
        // cartesian product check 

    }
}

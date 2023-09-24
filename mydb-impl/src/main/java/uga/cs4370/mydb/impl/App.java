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
        attr.add("sName");
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

        Relation table = new RelationImpl("Students", attr, type, list1);


        List<String> attr2 = new ArrayList<String>();
        attr2.add("Instructor");
        attr2.add("Number");
        attr2.add("Salary");
        attr2.add("Department");

        List<Type> type2 = new ArrayList<Type>();
        type.add(Type.STRING);
        type.add(Type.INTEGER);
        type.add(Type.INTEGER);
        type.add(Type.STRING);

        Cell trow1cell1 = new Cell("Mr.Brown");
        Cell trow1cell2 = new Cell(25);
        Cell trow1cell3 = new Cell(100000);
        Cell trow1cell4 = new Cell("Physics");
        
        Cell trow2cell1 = new Cell("Mr.Adams");
        Cell trow2cell2 = new Cell(55);
        Cell trow2cell3 = new Cell(200000);
        Cell trow2cell4 = new Cell("Bio");

        Cell trow3cell1 = new Cell("Mr.Smith");
        Cell trow3cell2 = new Cell(38);
        Cell trow3cell3 = new Cell(300000);
        Cell trow3cell4 = new Cell("CS");

        List<List<Cell>> list2 = List.of(
                 List.of(trow1cell1 ,trow1cell2, trow1cell3, trow1cell4), List.of(trow2cell1 ,trow2cell2, trow2cell3, trow2cell4), List.of(trow3cell1 ,trow3cell2, trow3cell3, trow3cell4)
         );
         // creates second table

        Relation table2 = new RelationImpl("Instructors", attr2, type2, list2);

        RA tester = new RAImpl();

        System.out.println("Table 1");

        table.print();
        System.out.println("Table 2");

        table2.print();

        System.out.println("");
        System.out.println("Start CP");
        tester.cartesianProduct(table, table2).print();
        System.out.println("End CP");
        // cartesian product check 



        System.out.println("");
        System.out.println("Start Join");
        tester.join(table, table2).print();
        System.out.println("End Join");
        // join check 

        /** 
        List<String> fattr = new ArrayList<String>();
        fattr.add("itemID");
        fattr.add("itemName");
        fattr.add("itemUnit");
        fattr.add("companyId");

        List<Type> ftype = new ArrayList<Type>();
        type.add(Type.INTEGER);
        type.add(Type.STRING);
        type.add(Type.STRING);
        type.add(Type.INTEGER);

       Cell frow1cell1 = new Cell(1);
       Cell frow1cell2 = new Cell("chex");
       Cell frow1cell3 = new Cell("pcs");
       Cell frow1cell4 = new Cell(16);
       
       Cell frow2cell1 = new Cell(6);
       Cell frow2cell2 = new Cell("cheese");
       Cell frow2cell3 = new Cell("pcs");
       Cell frow2cell4 = new Cell(15);

       Cell frow3cell1 = new Cell(2);
       Cell frow3cell2 = new Cell("bn");
       Cell frow3cell3 = new Cell("pcs");
       Cell frow3cell4 = new Cell(15);

       Cell frow4cell1 = new Cell(3);
       Cell frow4cell2 = new Cell("munch");
       Cell frow4cell3 = new Cell("pcs");
       Cell frow4cell4 = new Cell(17);

       Cell frow5cell1 = new Cell(4);
       Cell frow5cell2 = new Cell("pot");
       Cell frow5cell3 = new Cell("pcs");
       Cell frow5cell4 = new Cell(15);

       Cell frow6cell1 = new Cell(5);
       Cell frow6cell2 = new Cell("Jaffa");
       Cell frow6cell3 = new Cell("pcs");
       Cell frow6cell4 = new Cell(18);

       Cell frow7cell1 = new Cell(7);
       Cell frow7cell2 = new Cell("salt");
       Cell frow7cell3 = new Cell("pcs");
       Cell frow7cell4 = new Cell(0);

       List<List<Cell>> foodlist = List.of(
                List.of(frow1cell1 ,frow1cell2, frow1cell3, frow1cell4), List.of(frow2cell1 ,frow2cell2, frow2cell3, frow2cell4),
                List.of(frow3cell1 ,frow3cell2, frow3cell3, frow3cell4), List.of(frow4cell1 ,frow4cell2, frow4cell3, frow4cell4),
                List.of(frow5cell1 ,frow5cell2, frow5cell3, frow5cell4), List.of(frow6cell1 ,frow6cell2, frow6cell3, frow6cell4),
                List.of(frow7cell1 ,frow7cell2, frow7cell3, frow7cell4)
        );
        // creates first table
        Relation foodTable = new RelationImpl("Food", fattr, ftype, foodlist);




        List<String> cattr = new ArrayList<String>();
        cattr.add("companyId");
        cattr.add("companyName");
        cattr.add("companyCity");



        List<Type> ctype = new ArrayList<Type>();
        type.add(Type.INTEGER);
        type.add(Type.STRING);
        type.add(Type.STRING);

       Cell crow1cell1 = new Cell(18);
       Cell crow1cell2 = new Cell("order all");
       Cell crow1cell3 = new Cell("boston");

       Cell crow2cell1 = new Cell(15);
       Cell crow2cell2 = new Cell("jack hill");
       Cell crow2cell3 = new Cell("london");

       Cell crow3cell1 = new Cell(16);
       Cell crow3cell2 = new Cell("akas foods");
       Cell crow3cell3 = new Cell("dehil");

       Cell crow4cell1 = new Cell(17);
       Cell crow4cell2 = new Cell("foodies");
       Cell crow4cell3 = new Cell("london");

       Cell crow5cell1 = new Cell(19);
       Cell crow5cell2 = new Cell("sip-n-bite");
       Cell crow5cell3 = new Cell("ny");
       

       List<List<Cell>> complist = List.of(
                List.of(crow1cell1 ,crow1cell2, crow1cell3), List.of(crow2cell1 ,crow2cell2, crow2cell3),
                List.of(crow3cell1 ,crow3cell2, crow3cell3), List.of(crow4cell1 ,crow4cell2, crow4cell3),
                List.of(crow5cell1 ,crow5cell2, crow5cell3)
        );
        // creates first table

        Relation compTable = new RelationImpl("Company", cattr, ctype, complist);


       foodTable.print();
       System.out.println(foodTable.getAttrs());

        compTable.print();
        System.out.println(compTable.getAttrs());


     System.out.println("");
        System.out.println("Start JT");
        tester.join(foodTable, compTable).print();
        System.out.println("End JT");
*/







    }
}

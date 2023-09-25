package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Type;

import java.util.ArrayList;
import java.util.List;

public class RelationImpl implements Relation {

    private String name;
    private List<String> attrs;
    private List<Type> types;
    private List<List<Cell>> rows = new ArrayList<>();

    public RelationImpl(String name, List<String> attrs, List<Type> types) {
        this.name = name;
        this.attrs = attrs;
        this.types = types;
    }

    public RelationImpl(String name, List<String> attrs, List<Type> types, List<List<Cell>> rows) {
        this.name = name;
        this.attrs = attrs;
        this.types = types;
        for (int i = 0; i  < rows.size(); i++) {
            insert(rows.get(i));
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return rows.size();
    }

    @Override
    public List<List<Cell>> getRows() {
        List<List<Cell>> deepCopy = new ArrayList<>();

        for (List<Cell> row : rows) {
            List<Cell> newRow = new ArrayList<>();
            for (Cell cell : row) {
                newRow.add(cell);
            }
            deepCopy.add(newRow);
        }

        return deepCopy;
    }

    @Override
    public List<Type> getTypes() {
        return types;
    }

    @Override
    public List<String> getAttrs() {
        return attrs;
    }

    @Override
    public boolean hasAttr(String attr) {
        return attrs.contains(attr);
    }

    @Override
    public int getAttrIndex(String attr) {
        int index = attrs.indexOf(attr);
        if (index == -1) {
            throw new IllegalArgumentException("Attribute does not exist in the relation.");
        }
        return index;
    }

    @Override
    public void insert(Cell... cells) {
        insert(List.of(cells));
    }

    @Override
    public void insert(List<Cell> cells) {
        if (cells.size() != types.size()) {
            throw new IllegalArgumentException("The number of cells doesn't match the relation's number of columns.");
        }
        for (int i = 0; i < cells.size(); i++) {
            Type findType = findType(cells.get(i));
            if (findType != types.get(i)) {
                throw new IllegalArgumentException("Cell type does not match column type.");
            }
        }
        rows.add(new ArrayList<>(cells));
    }

    public Type findType(Cell cell) {
        try {
            cell.getAsInt();
            return Type.INTEGER;
        } catch (Exception e1) {
            try {
                cell.getAsDouble();
                return Type.DOUBLE;
            } catch (Exception e2) {
                try {
                    cell.getAsString();
                    return Type.STRING;
                } catch (Exception e3) {
                    throw new IllegalStateException("Invalid cell type");
                }
            }
        }
    }

    @Override
    public void print() {
        int cellWidth = 20; 
        String horizontalBorder = "+" + "-".repeat(cellWidth);
    
        System.out.println(name);
    
        // Print top border
        for (int i = 0; i < attrs.size(); i++) {
            System.out.print(horizontalBorder);
        }
        System.out.println("+");
    
        // Print attributes
        System.out.print("|");
        for (String attr : attrs) {
            // Truncate or pad the attribute to fit the cell width
            String formattedAttr = (attr + " ".repeat(cellWidth)).substring(0, cellWidth);
            System.out.print(formattedAttr + "|");
        }
        System.out.println();
    
        // Print attributes border
        for (int i = 0; i < attrs.size(); i++) {
            System.out.print(horizontalBorder);
        }
        System.out.println("+");
    
        for (List<Cell> row : rows) {
            System.out.print("|");
            for (Cell cell : row) {
                String cellValue = cell.toString();
                String formattedCellValue = (cellValue + " ".repeat(cellWidth)).substring(0, cellWidth);
                System.out.print(formattedCellValue + "|");
            }
            System.out.println();
    
            // Print row border
            for (int i = 0; i < row.size(); i++) {
                System.out.print(horizontalBorder);
            }
            System.out.println("+");
        }
        System.out.println("");
    }
    
    
    
}  


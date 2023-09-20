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
    private List<List<Cell>> rows;

    public RelationImpl(String name, List<String> attrs, List<Type> types) {
        this.name = name;
        this.attrs = attrs;
        this.types = types;
        this.rows = new ArrayList<>();
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
            if (cells.get(i).getType() != types.get(i)) {
                throw new IllegalArgumentException("The cell type doesn't match the column type.");
            }
        }
        rows.add(new ArrayList<>(cells));
    }

    @Override
    public void print() {
        
        System.out.println(name);
        System.out.println(attrs);
        for (List<Cell> row : rows) {
            System.out.println(row);
        }
    }
}  


package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.List;

import uga.cs4370.mydb.*;

public class RAImpl implements RA {

    /**
     * Performs the select operation on the relation rel
     * by applying the predicate p.
     * 
     * @return The resulting relation after applying the select operation.
     */
    @Override
    public Relation select(Relation rel, Predicate p) {
        List<List<Cell>> newRelation = new ArrayList<>();
        for (List<Cell> row : rel.getRows()) {
            if (p.check(row)) {
                newRelation.add(new ArrayList<>(row));
            }
        }
        return new RelationImpl(rel.getName(), rel.getAttrs(), rel.getTypes(), newRelation);
    }

    /**
     * Performs the project operation on the relation rel
     * given the attributes list attrs.
     * 
     * @return The resulting relation after applying the project operation.
     * 
     * @throws IllegalArgumentException If attributes in attrs are not 
     * present in rel.
     */
    @Override
    public Relation project(Relation rel, List<String> attrs) {
        return new RelationImpl(rel.getName(), rel.getAttrs(), rel.getTypes());
    }

    /**
     * Performs the union operation on the relations rel1 and rel2.
     * 
     * @return The resulting relation after applying the union operation.
     * 
     * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
     */
    @Override
    public Relation union(Relation rel1, Relation rel2){
        return new RelationImpl(rel1.getName(), rel1.getAttrs(), rel1.getTypes());

    }

    /**
     * Performs the set difference operation on the relations rel1 and rel2.
     * 
     * @return The resulting relation after applying the set difference operation.
     * 
     * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
     */
    @Override
    public Relation diff(Relation rel1, Relation rel2){
        try {
            // Check that the relations are compatible
            if(rel1.getAttrs == rel2.getAttrs && rel1.getTypes == rel2.getTypes) {
                String newRelation = "Relation After Set Difference";
                Relation newTable = new RelationImpl(newRelation, rel1.getAttrs, rel1.getTypes);
                List<List<Cell>> newRows;
                for(List<Cell> eachRow1 : rel1.getRows()) {
                    boolean equal = false;
                    for(List<Cell> eachRow2 : rel2.getRows()) {
                        if(eachRow1.equals(eachRow2)) {
                            equal=true;
                            break;
                        }
                    if(!equal) {
                        newRows.add(eachRow1);
                    }
                    } 
                }
            }
        }
        catch (IllegalArgumentException iae) {
            System.out.println("rel1 and rel2 are not compatible");
        }    
        return new RelationImpl(newTable.getName(), newTable.getAttrs(), newTable.getTypes(),newRows);
    }

    /**
     * Renames the attributes in origAttr of relation rel to corresponding 
     * names in renamedAttr.
     * 
     * @return The resulting relation after renaming the attributes.
     * 
     * @throws IllegalArgumentException If attributes in origAttr are not present in 
     * rel or origAttr and renamedAttr do not have matching argument counts.
     */
    @Override
    public Relation rename(Relation rel, List<String> origAttr, List<String> renamedAttr){
        try  {
            // Checking conditions
            boolean present = true;
            boolean size = false;
            if (origAttr.getSize() == renamedAttr.getSize()) {
                size=true;
                for (String attribute : origAttr) {
                    if(!(rel.hasAttr(attribute))) {
                        present = false;
                        break;
                    } 
                }
            } 
            if(present && size) {
                for (i=0; i < origAttr.getSize(); i++) {
                    rel1.getAttrs().set(i,renamedAttr.get(i));
                }
            }

        } catch (IllegalArgumentException iae) {
            if(!present) {
                System.out.println("Attributes in origAttr are not present in rel attributes.");
            }
            if (!size) {
                System.out.println("origAttr and renamedAttr do not have matching argument counts");
            }
        }
        return new RelationImpl(rel.getName(), rel.getAttrs(), rel.getTypes());

    }

    /**
     * Performs cartisian product on relations rel1 and rel2.
     * 
     * @return The resulting relation after applying cartisian product.
     * 
     * @throws IllegalArgumentException if rel1 and rel2 have common attibutes.
     */
    @Override
    public Relation cartesianProduct(Relation rel1, Relation rel2){
        String mergeName = rel1.getName() + " x " + rel2.getName();

        List<String> mergeAttr = new ArrayList<>();
        mergeAttr.addAll(rel1.getAttrs());
        mergeAttr.addAll(rel2.getAttrs());

        List<Type> mergeType = new ArrayList<>();
        mergeType.addAll(rel1.getTypes());
        mergeType.addAll(rel2.getTypes());

       Relation newTable = new RelationImpl(mergeName, mergeAttr, mergeType);

       List<List<Cell>> newRelation = new ArrayList<>();
        for (List<Cell> row1 : rel1.getRows()) {
            for (List<Cell> row2 : rel2.getRows()) {
                List<Cell> mergeRow = new ArrayList<>();
                mergeRow.addAll(row1);
                mergeRow.addAll(row2);
                newRelation.add(mergeRow);
            } // for 
        } // for

        return new RelationImpl(newTable.getName(), newTable.getAttrs(), newTable.getTypes(), newRelation);
    }

    /**
     * Peforms natural join on relations rel1 and rel2.
     * 
     * @return The resulting relation after applying natural join.
     */
    @Override
    public Relation join(Relation rel1, Relation rel2){
        return new RelationImpl(rel1.getName(), rel1.getAttrs(), rel1.getTypes());

    }

    /**
     * Performs theta join on relations rel1 and rel2 with predicate p.
     * 
     * @return The resulting relation after applying theta join.
     */
    @Override
    public Relation join(Relation rel1, Relation rel2, Predicate p){
        return new RelationImpl(rel1.getName(), rel1.getAttrs(), rel1.getTypes());


    }

}

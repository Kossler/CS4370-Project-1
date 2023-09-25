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
        List<List<Cell>> newRelation = new ArrayList<>();
        List<String> presentAttrs = rel.getAttrs();
        List<Type> presentTypes = rel.getTypes();
        List<Type> projectedTypes = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        for (String attr : attrs) {
            if (!presentAttrs.contains(attr)) {
                throw new IllegalArgumentException("Attributes in attrs are not present in relation.");
            } else {
                int index = presentAttrs.indexOf(attr);
                indices.add(index);
                projectedTypes.add(presentTypes.get(index));
            }
        }

        for (List<Cell> row : rel.getRows()) {
            List<Cell> newRow = new ArrayList<>();
            for (int index : indices) {
                newRow.add(row.get(index));
            }
            newRelation.add(newRow);
        }

        return new RelationImpl(rel.getName(), attrs, projectedTypes, newRelation);
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
        List<List<Cell>> newRelation = new ArrayList<>();
        List<String> attrsRel1 = rel1.getAttrs();
        List<String> attrsRel2 = rel2.getAttrs();
        List<Type> typesRel1 = rel1.getTypes();
        List<Type> typesRel2 = rel2.getTypes();

        if (attrsRel1.size() != attrsRel2.size() || typesRel1.equals(typesRel2) == false) {
            throw new IllegalArgumentException("Relation 1 and relation 2 are not compatible.");
        }

        for (List<Cell> row1 : rel1.getRows()) {
            newRelation.add(new ArrayList<>(row1));
            }

        for (List<Cell> row2 : rel2.getRows()) {
                if (rel1.getRows().equals(row2) == false) {
                    newRelation.add(new ArrayList<>(row2));
                }
        }

        return new RelationImpl(rel1.getName(), rel1.getAttrs(), rel1.getTypes(), newRelation);

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
        String newRelation = "Relation After Set Difference";
        Relation newTable = new RelationImpl(newRelation, rel1.getAttrs(), rel1.getTypes());
        List<List<Cell>> newRows = new ArrayList<>();
        try {
            // Check that the relations are compatible
            if(rel1.getAttrs() == rel2.getAttrs() && rel1.getTypes() == rel2.getTypes()) {  
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
        return new RelationImpl(newTable.getName(), newTable.getAttrs(), newTable.getTypes(), newRows);
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
            // Checking conditions
            boolean present = true;
            boolean size = false;
        try  {
            if (origAttr.size() == renamedAttr.size()) {
                size=true;
                for (String attribute : origAttr) {
                    if(!(rel.hasAttr(attribute))) {
                        present = false;
                        break;
                    } 
                }
            } 
            if(present && size) {
                for (int i = 0; i < origAttr.size(); i++) {
                    rel.getAttrs().set(i,renamedAttr.get(i));
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
    public Relation join(Relation rel1, Relation rel2) {
        List<Integer> commonAttrsFromRel1 = new ArrayList<>();
        List<Integer> commonAttrsFromRel2 = new ArrayList<>();

        // Find common attributes
        for (int a = 0; a < rel1.getAttrs().size(); a++) {
            for (int b = 0; b < rel2.getAttrs().size(); b++) {
                if (rel1.getAttrs().get(a).equals(rel2.getAttrs().get(b))) {
                    commonAttrsFromRel1.add(a);
                    commonAttrsFromRel2.add(b);
                }
            }
        }

        // If no common attributes, return cartesian product
        if (commonAttrsFromRel1.isEmpty()) {
            return this.cartesianProduct(rel1, rel2);
        }

        String mergeName = rel1.getName() + " x " + rel2.getName();
        List<String> mergedAttrs = new ArrayList<>(rel1.getAttrs());
        mergedAttrs.addAll(rel2.getAttrs());
        List<Type> mergedTypes = new ArrayList<>(rel1.getTypes());
        mergedTypes.addAll(rel2.getTypes());

        // Remove duplicated attributes and their corresponding types
        for (int index : commonAttrsFromRel2) {
            mergedAttrs.remove(rel1.getAttrs().size() + index);
            mergedTypes.remove(rel1.getTypes().size() + index);
        }

        List<List<Cell>> mergedRows = new ArrayList<>();
        for (List<Cell> row1 : rel1.getRows()) {
            for (List<Cell> row2 : rel2.getRows()) {
                boolean shouldMerge = true;
                for (int i = 0; i < commonAttrsFromRel1.size(); i++) {
                    if (!row1.get(commonAttrsFromRel1.get(i)).equals(row2.get(commonAttrsFromRel2.get(i)))) {
                        shouldMerge = false;
                        break;
                    }
                }
                
                if (shouldMerge) {
                    List<Cell> mergedRow = new ArrayList<>(row1);
                    for (int i = 0; i < row2.size(); i++) {
                        if (!commonAttrsFromRel2.contains(i)) {
                            mergedRow.add(row2.get(i));
                        }
                    }
                    mergedRows.add(mergedRow);
                }
            }
        }

        return new RelationImpl(mergeName, mergedAttrs, mergedTypes, mergedRows);
    }


    /**
     * Performs theta join on relations rel1 and rel2 with predicate p.
     * 
     * @return The resulting relation after applying theta join.
     */
    @Override
    public Relation join(Relation rel1, Relation rel2, Predicate p) {
        String mergeName = rel1.getName() + " x " + rel2.getName();

        List<String> mergeAttr = new ArrayList<>();
        mergeAttr.addAll(rel1.getAttrs());
        mergeAttr.addAll(rel2.getAttrs());

        List<Type> mergeType = new ArrayList<>();
        mergeType.addAll(rel1.getTypes());
        mergeType.addAll(rel2.getTypes());

        List<List<Cell>> newRelation = new ArrayList<>();
        for (List<Cell> row1 : rel1.getRows()) {
            for (List<Cell> row2 : rel2.getRows()) {
                List<Cell> mergedRow = new ArrayList<>(row1);
                mergedRow.addAll(row2);

                if (p.check(mergedRow)) {
                    newRelation.add(mergedRow);
                }
            }
        }

        return new RelationImpl(mergeName, mergeAttr, mergeType, newRelation);
    }


}

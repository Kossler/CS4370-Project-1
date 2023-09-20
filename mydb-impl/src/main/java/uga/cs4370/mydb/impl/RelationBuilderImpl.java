package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Relation;
import java.util.List;

public class RelationBuilderImpl implements RelationBuilder {

    @Override
    public Relation newRelation(String name, List<String> attrs, List<Type> types) {
      if (attrs.size() != types.size()) {
        throw new IllegalArgumentException("The number of attributes and types are not equal.");
      }
      if (attrs.isEmpty()) {
        throw new IllegalArgumentException("There are no attributes assigned to the relationship.");
      }
      if (attrs.getClass().equals(String.class)) {
        throw new IllegalArgumentException("Attributes must be strings.");
      }
      RelationImpl relation = new RelationImpl(name, attrs, types);
      return relation;
    }
}

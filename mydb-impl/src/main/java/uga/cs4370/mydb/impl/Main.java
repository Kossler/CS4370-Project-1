package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.*;
import java.util.*;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ) {

        // Student Table
        List<String> studentAttrs = new ArrayList<>();
        studentAttrs.add("StudentID");
        studentAttrs.add("FName");
        studentAttrs.add("LName");
        studentAttrs.add("DoB");
        studentAttrs.add("Major");

        List<Type> studentTypes = new ArrayList<>();
        studentTypes.add(Type.INTEGER);
        studentTypes.add(Type.STRING);
        studentTypes.add(Type.STRING);
        studentTypes.add(Type.STRING);
        studentTypes.add(Type.STRING);

        // Courses Table
        List<String> coursesAttrs = new ArrayList<>();
        coursesAttrs.add("CourseID");
        coursesAttrs.add("CName");
        coursesAttrs.add("Credits");

        List<Type> coursesTypes = new ArrayList<>();
        coursesTypes.add(Type.INTEGER);
        coursesTypes.add(Type.STRING);
        coursesTypes.add(Type.INTEGER);

        // Enrollment Table
        List<String> enrollmentAttrs = new ArrayList<>();
        enrollmentAttrs.add("EnrollmentID");
        enrollmentAttrs.add("StudentID");
        enrollmentAttrs.add("CourseID");
        enrollmentAttrs.add("Grade");

        List<Type> enrollmentTypes = new ArrayList<>();
        enrollmentTypes.add(Type.INTEGER);
        enrollmentTypes.add(Type.INTEGER);
        enrollmentTypes.add(Type.INTEGER);
        enrollmentTypes.add(Type.STRING);

        // Professors Table
        List<String> professorsAttrs = new ArrayList<>();
        professorsAttrs.add("ProfessorID");
        professorsAttrs.add("FName");
        professorsAttrs.add("LName");
        professorsAttrs.add("Department");

        List<Type> professorsTypes = new ArrayList<>();
        professorsTypes.add(Type.INTEGER);
        professorsTypes.add(Type.STRING);
        professorsTypes.add(Type.STRING);
        professorsTypes.add(Type.STRING);

        // Teaches Table
        List<String> teachesAttrs = new ArrayList<>();
        teachesAttrs.add("TeachID");
        teachesAttrs.add("ProfessorID");
        teachesAttrs.add("CourseID");

        List<Type> teachesTypes = new ArrayList<>();
        teachesTypes.add(Type.INTEGER);
        teachesTypes.add(Type.INTEGER);
        teachesTypes.add(Type.INTEGER);       
        
        // Populate Tables
        List<List<Cell>> students = populateStudents();
        List<List<Cell>> courses = populateCourses();
        List<List<Cell>> enrollment = populateEnrollment();
        List<List<Cell>> professors = populateProfessors();
        List<List<Cell>> teaches = populateTeaches();
        
        Relation studentsTable = new RelationImpl("Students", studentAttrs, studentTypes, students);
        Relation coursesTable = new RelationImpl("Courses", coursesAttrs, coursesTypes, courses);
        Relation enrollmentTable = new RelationImpl("Enrollment", enrollmentAttrs, enrollmentTypes, enrollment);
        Relation professorsTable = new RelationImpl("Professors", professorsAttrs, professorsTypes, professors);
        Relation teachesTable = new RelationImpl("Teaches", teachesAttrs, teachesTypes, teaches);

        // Print Tables
        studentsTable.print();
        coursesTable.print();
        enrollmentTable.print();
        professorsTable.print();
        teachesTable.print();

        // Question 1
        Predicate p1 = new IntegerEqual(enrollmentTable.getAttrIndex("StudentID"), 1234);
        RA RA = new RAImpl();
        Relation question1 = RA.select(enrollmentTable, p1);
        List<String> filterAttr = new ArrayList<>();
        filterAttr.add("CourseID");
        question1 = RA.project(question1, filterAttr);
        question1.print();

        // Question 2
        RA = new RAImpl();
        Predicate p2 = new StringEqual(studentsTable.getAttrIndex("Major"), "Computer Science");
        Relation question2 = RA.select(studentsTable, p2);
        filterAttr = new ArrayList<>();
        filterAttr.add("StudentID");
        filterAttr.add("FName");
        filterAttr.add("LName");
        question2 = RA.project(question2, filterAttr);
        question2.print();

        // Question 3
        

    }

    public static List<List<Cell>> populateStudents() {
        List<List<Cell>> students = new ArrayList<>();

        List<Cell> johnDoe = new ArrayList<>();
        johnDoe.add(new Cell(1234));
        johnDoe.add(new Cell("John"));
        johnDoe.add(new Cell("Doe"));
        johnDoe.add(new Cell("1999-09-21"));
        johnDoe.add(new Cell("Computer Science"));

        students.add(johnDoe);

        List<Cell> janeSmith = new ArrayList<>();
        janeSmith.add(new Cell(2345));
        janeSmith.add(new Cell("Jane"));
        janeSmith.add(new Cell("Smith"));
        janeSmith.add(new Cell("2001-02-28"));
        janeSmith.add(new Cell("Business"));

        students.add(janeSmith);

        List<Cell> aliceJohnson = new ArrayList<>();
        aliceJohnson.add(new Cell(3456));
        aliceJohnson.add(new Cell("Alice"));
        aliceJohnson.add(new Cell("Johnson"));
        aliceJohnson.add(new Cell("2003-07-14"));
        aliceJohnson.add(new Cell("Physics"));

        students.add(aliceJohnson);

        List<Cell> jakeNeil = new ArrayList<>();
        jakeNeil.add(new Cell(6789));
        jakeNeil.add(new Cell("Jake"));
        jakeNeil.add(new Cell("Neil"));
        jakeNeil.add(new Cell("2002-12-02"));
        jakeNeil.add(new Cell("Computer Science"));

        students.add(jakeNeil);

        return students;
    }

    public static List<List<Cell>> populateCourses() {
        List<List<Cell>> courses = new ArrayList<>();

        List<Cell> course1 = new ArrayList<>();
        course1.add(new Cell(1101));
        course1.add(new Cell("Introduction to CS"));
        course1.add(new Cell(3));

        courses.add(course1);

        List<Cell> course2 = new ArrayList<>();
        course2.add(new Cell(1102));
        course2.add(new Cell("Calculus I"));
        course2.add(new Cell(4));

        courses.add(course2);

        List<Cell> course3 = new ArrayList<>();
        course3.add(new Cell(1103));
        course3.add(new Cell("Physics I"));
        course3.add(new Cell(2));

        courses.add(course3);

        return courses;
    }

    public static List<List<Cell>> populateEnrollment() {
        List<List<Cell>> enrollment = new ArrayList<>();

        List<Cell> enrollment1 = new ArrayList<>();
        enrollment1.add(new Cell(1));
        enrollment1.add(new Cell(1234));
        enrollment1.add(new Cell(1101));
        enrollment1.add(new Cell("A"));

        enrollment.add(enrollment1);

        List<Cell> enrollment2 = new ArrayList<>();
        enrollment2.add(new Cell(2));
        enrollment2.add(new Cell(1234));
        enrollment2.add(new Cell(1102));
        enrollment2.add(new Cell("F"));

        enrollment.add(enrollment2);

        List<Cell> enrollment3 = new ArrayList<>();
        enrollment3.add(new Cell(3));
        enrollment3.add(new Cell(3456));
        enrollment3.add(new Cell(1102));
        enrollment3.add(new Cell("C"));

        enrollment.add(enrollment3);

        List<Cell> enrollment4 = new ArrayList<>();
        enrollment4.add(new Cell(4));
        enrollment4.add(new Cell(6789));
        enrollment4.add(new Cell(1103));
        enrollment4.add(new Cell("2"));

        enrollment.add(enrollment4);

        return enrollment;
    }

    public static List<List<Cell>> populateProfessors() {
        List<List<Cell>> professors = new ArrayList<>();

        List<Cell> professor1 = new ArrayList<>();
        professor1.add(new Cell(1));
        professor1.add(new Cell("Dr. Bob"));
        professor1.add(new Cell("Williams"));
        professor1.add(new Cell("Computer Science"));

        professors.add(professor1);

        List<Cell> professor2 = new ArrayList<>();
        professor2.add(new Cell(2));
        professor2.add(new Cell("Dr. Anne"));
        professor2.add(new Cell("Taylor"));
        professor2.add(new Cell("Mathematics"));

        professors.add(professor2);

        List<Cell> professor3 = new ArrayList<>();
        professor3.add(new Cell(3));
        professor3.add(new Cell("Dr. John"));
        professor3.add(new Cell("Adams"));
        professor3.add(new Cell("Physics"));

        professors.add(professor3);

        return professors;
    }

    public static List<List<Cell>> populateTeaches() {
        List<List<Cell>> teaches = new ArrayList<>();

        List<Cell> teaches1 = new ArrayList<>();
        teaches1.add(new Cell(1));
        teaches1.add(new Cell(1));
        teaches1.add(new Cell(1101));

        teaches.add(teaches1);

        List<Cell> teaches2 = new ArrayList<>();
        teaches2.add(new Cell(1));
        teaches2.add(new Cell(2));
        teaches2.add(new Cell(1102));

        teaches.add(teaches2);

        return teaches;
    }
    
}



package edu.gatech.seclass.project3;

import java.util.HashMap;
import java.util.HashSet;

public class Course {
	Students studentDB;
	Grades gradeDB;

	public Course(String db) {
		studentDB = new Students(db);
		gradeDB = new Grades(db);
	}

	public int getNumStudents() {
		return studentDB.numOfStudents();
	}

	public int getNumAssignments() {
		return gradeDB.getNumOfAssignment();
	}

	public int getNumProjects() {
		return gradeDB.getNumOfProject();
	}

	public HashSet<Student> getStudents() {
		return studentDB.getStudents();
	}

	public Student getStudentByName(String name) {
		return studentDB.getStudentByName(name);
	}

	public Student getStudentByID(int gtId) {
		return studentDB.getStudentById(gtId);
	}

	public void addAssignment(String assignment) {
		gradeDB.addAssignment(assignment);
		
	}

	public void updateGrades(Grades grades) {
		
		
	}

	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> grades) {
		
		gradeDB.addGradesForAssignment(assignmentName, grades);
		
	}

	public int getAverageAssignmentsGrade(Student student) {
		return gradeDB.getAverageAssignmentsGrade(student);
	}

	public int getAverageProjectsGrade(Student student) {
		return gradeDB.getAverageProjectsGrade(student);
	}

	public void addIndividualContributions(String projectName1,
			HashMap<Student, Integer> contributions1) {
		gradeDB.addIndividualContributions(projectName1, contributions1);
	}
	
	
	
	

}

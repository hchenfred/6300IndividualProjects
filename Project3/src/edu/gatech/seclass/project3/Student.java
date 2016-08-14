package edu.gatech.seclass.project3;

public class Student {
	private String name;
	private int gtID;
	private int attendance;
	private String team;
	private String email;
	private Course course;
	
	public Student() {
		
	};
	
	public Student(String name, int gtID, Course course) {
		this.name = name;
		this.gtID = gtID;
		this.course = course;
	}
	

	public String getName() {
		return this.name;
	}

	public int getAttendance() {
		return this.attendance;
	}

	public int getGtid() {
		return this.gtID;
	}

	public String getTeam() {
		return this.team;
	}

	public void setGtID(int gtID) {
		this.gtID = gtID;
		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;	
	}
	
	public void setTeam(String team) {
		this.team = team;
	}

	public void setEmail(String email) {
		this.email = email;
		
	}
	
	public String getTeamById(int gtId) {
		return course.getStudentByID(gtId).getTeam();
	}
	
}

package edu.gatech.seclass.project3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Students {
	private XSSFWorkbook workbook;
	private HashMap<Integer, Student> gtIdToStudent; 
	
	public Students(String db) {
		try {	     
		    FileInputStream file = new FileInputStream(new File(db));
		     
		    //Get the workbook instance for XLS file 
		    workbook = new XSSFWorkbook(file);
		    workbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);

		    gtIdToStudent = new HashMap<Integer, Student>();
		    //Get "Student" sheet from the workbook
		    XSSFSheet sheet = workbook.getSheetAt(0);
		    
			//Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    //Skip first row
		    Row row = rowIterator.next();
		    while(rowIterator.hasNext()) {
		        row = rowIterator.next();
		        Student student = new Student();
		        student.setName(row.getCell(0).getStringCellValue());
		        student.setGtID((int)row.getCell(1).getNumericCellValue());
		        student.setEmail(row.getCell(2).getStringCellValue());
		        gtIdToStudent.put(student.getGtid(), student);
		    }
		    
		    //Get "Attendance" sheet from the workbook
		    sheet = workbook.getSheetAt(2);
		    
			//Iterate through each rows from third sheet
		    rowIterator = sheet.iterator();
		    //Skip first row
		    row = rowIterator.next();
		    while(rowIterator.hasNext()) {
		        row = rowIterator.next();
		        gtIdToStudent.get((int)row.getCell(0).getNumericCellValue()).setAttendance((int)row.getCell(1).getNumericCellValue());		              
		    }
		    
		    //Get "Teams" sheet from the workbook
		    sheet = workbook.getSheetAt(1);
			//Iterate through each rows from second sheet
		    rowIterator = sheet.iterator();
		    //Skip first row
		    row = rowIterator.next();
		    while(rowIterator.hasNext()) {
		        row = rowIterator.next();
		        if (row == null) {
		        	continue;
		        }
		        String team = row.getCell(0).getStringCellValue();
		   
        	   for (int i = 1; i <= 4; i++) {
		        	Cell c = row.getCell(i);
		        	if (c.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
		        		continue;
		        	} else {
		        		gtIdToStudent.get((int)c.getNumericCellValue()).setTeam(team);;
		        	}
		        }	
		     
		    }
		    file.close();
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	
	}
	
	public int numOfStudents() {
		int count = 0;
	    //Get first sheet from the workbook
	    XSSFSheet sheet = workbook.getSheetAt(0);
	    count =  sheet.getPhysicalNumberOfRows();	     
		return count - 1;
	}
	
	public HashSet<Student> getStudents() {	
	    return new HashSet<Student>(gtIdToStudent.values());	
	}
	
	public Student getStudentByName(String name) {
		for (Student s : gtIdToStudent.values()) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	public Student getStudentById(int gtId) {
		return gtIdToStudent.get(gtId);
	}
	

}

package edu.gatech.seclass.project3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Grades {
	XSSFWorkbook workbook;
	int numOfAssignment = 0;;
	int numOfProject = 0;
	String filePath;

	public Grades(String db) {
		filePath = db;
		try {	     
		    FileInputStream file = new FileInputStream(new File(db));
		     
		    //Get the workbook instance for XLS file 
		    workbook = new XSSFWorkbook(file);
		    workbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
			//Get "IndividualGrades" sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(3);
			numOfAssignment =  sheet.getRow(0).getPhysicalNumberOfCells() - 1;
			
			//Get "IndividualContribs" sheet from the workbook
			sheet = workbook.getSheetAt(4);
			numOfProject =  sheet.getRow(0).getPhysicalNumberOfCells() - 1;
			file.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public int getNumOfAssignment() {	
		return numOfAssignment;
	}
	
	public int getNumOfProject() {
		return numOfProject;
	}
	
	public void addAssignment(String assignment) {
		try {
		    FileInputStream file = new FileInputStream(new File(filePath));
		    //Get the workbook instance for XLS file 
		    workbook = new XSSFWorkbook(file);
		    workbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
			Cell cell = null;
			//Get "IndividualGrades" sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(3);
			if (!isAssignmentExist(assignment)) {
				cell = sheet.getRow(0).getCell(numOfAssignment + 1);
				cell.setCellValue(assignment);
			    file.close();		     
			    FileOutputStream outFile =new FileOutputStream(new File(filePath));
			    workbook.write(outFile);
			    outFile.close();
			    numOfAssignment++;
				
			} 	
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> grades) {
		try {
		    FileInputStream file = new FileInputStream(new File(filePath));
		    //Get the workbook instance for XLS file 
		    workbook = new XSSFWorkbook(file);
		    workbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
		    XSSFSheet sheet = workbook.getSheetAt(3);
		    int col = findCol(sheet, assignmentName);
		    if (col == -1) {
		    	System.out.println("Assignment not found.");
		    } else {
		        for (Map.Entry<Student, Integer> entry : grades.entrySet()) {
			        Student student = entry.getKey();
			        int row = findRow(sheet, student.getGtid());
			        Cell cell = null;
			        cell = sheet.getRow(row).getCell(col);
			        cell.setCellValue(entry.getValue());		        
				}
		    	
		    }
		    file.close();		     
		    FileOutputStream outFile =new FileOutputStream(new File(filePath));
		    workbook.write(outFile);
		    outFile.close();
		    numOfAssignment++;
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
	}
	
	public int findCol(XSSFSheet sheet, String cellContent) {
		for (int i = 0; i <sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
			if (sheet.getRow(0).getCell(i).getStringCellValue().equals(cellContent)) {
				return i;
			}
		}
		return -1;
	}
	
	public int findRow(XSSFSheet sheet, int gtID) {
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			//System.out.println(sheet.getRow(i).getCell(0).getCellType());
			if ((int)sheet.getRow(i).getCell(0).getNumericCellValue() == gtID) {
				return i;
			}
		}
		return -1;
	}
	
	public int getAverageAssignmentsGrade(Student student) {
		XSSFSheet sheet = workbook.getSheetAt(3);    
		int row = findRow(sheet, student.getGtid());
		double sum = 0;
		int count = 0;
		for (int i = 1; i <= numOfAssignment; i++) {
			Cell c = sheet.getRow(row).getCell(i);
			if (c.getCellType() != HSSFCell.CELL_TYPE_BLANK) {
				sum = sum + (int)c.getNumericCellValue();
				count++;
				//System.out.println((int)sheet.getRow(row).getCell(i).getNumericCellValue());		
			}
		
		}
		return (int)Math.round(sum/count);
	}
	
	private boolean isAssignmentExist(String assignment) {
		XSSFSheet sheet = workbook.getSheetAt(3);    
		for (int i = 1; i <= numOfAssignment; i++) {
			if (sheet.getRow(0).getCell(i).getStringCellValue().equals(assignment)) {
				return true;
			}
		}
		return false;
	}
	
	public int getAverageProjectsGrade(Student student) {
		//find the row in individualContribs
		XSSFSheet sheet1 = workbook.getSheetAt(4); 
		int individualContribsRow = -1;
		for (int i = 1; i < sheet1.getPhysicalNumberOfRows(); i++) {
			//System.out.println("" + (int)sheet1.getRow(i).getCell(0).getNumericCellValue() );
			if ((int)sheet1.getRow(i).getCell(0).getNumericCellValue() == student.getGtid()) {
				individualContribsRow = i;
			}
		}
		
		//find the row in TeamGrades
		XSSFSheet sheet2 = workbook.getSheetAt(5); 
		int teamGradeRow = -1;
		for (int i = 1; i < sheet2.getPhysicalNumberOfRows(); i++) {
			//System.out.println("" + student.getGtid());
			if (sheet2.getRow(i).getCell(0).getStringCellValue().equals(student.getTeamById(student.getGtid()))) {
				teamGradeRow = i;
			}
		}
		double sum = 0;
		if (individualContribsRow != -1 && teamGradeRow != -1) {
			sum = sheet1.getRow(individualContribsRow).getCell(1).getNumericCellValue() * sheet2.getRow(teamGradeRow).getCell(1).getNumericCellValue() + 
					sheet1.getRow(individualContribsRow).getCell(2).getNumericCellValue() * sheet2.getRow(teamGradeRow).getCell(2).getNumericCellValue() + 
					sheet1.getRow(individualContribsRow).getCell(3).getNumericCellValue() * sheet2.getRow(teamGradeRow).getCell(3).getNumericCellValue();	
		}
		
		
		return (int)Math.round(sum/numOfProject/100);		
	}
	
	public void addIndividualContributions(String projectName1,
			HashMap<Student, Integer> contributions1) {
		
		try {
		    FileInputStream file = new FileInputStream(new File(filePath));
		    //Get the workbook instance for XLS file 
		    workbook = new XSSFWorkbook(file);
		    workbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
			//get individualContribs sheet
			XSSFSheet sheet = workbook.getSheetAt(4); 
			int projectCol = findCol(sheet, projectName1);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				for (Map.Entry<Student, Integer> entry : contributions1.entrySet()) {
					if ((int)sheet.getRow(i).getCell(0).getNumericCellValue() == entry.getKey().getGtid()) {
						Cell cell = null;
				        cell = sheet.getRow(i).getCell(projectCol);
				        cell.setCellValue(entry.getValue());							
					}        
				}
				
			}
		    file.close();		     
		    FileOutputStream outFile =new FileOutputStream(new File(filePath));
		    workbook.write(outFile);
		    outFile.close();		
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}	
	}
	
	
	
	
}

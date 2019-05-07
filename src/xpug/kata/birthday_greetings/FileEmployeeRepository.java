package xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {
	
	private String filename;
	
	public FileEmployeeRepository(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public List<Employee> getAllEmployees() throws ParseException, IOException {
		List<Employee> employees = new ArrayList<Employee>();
		System.out.println("Abriendo archivo");
		@SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String str = "";
		str = in.readLine(); // skip header
		System.out.println("Primera linea de archivo");
		while ((str = in.readLine()) != null) {
			String[] employeeData = str.split(", ");
			Employee employee = new Employee(employeeData[1], employeeData[0],
					employeeData[2], employeeData[3]);
			employees.add(employee);
		}
		return employees;
	}
}

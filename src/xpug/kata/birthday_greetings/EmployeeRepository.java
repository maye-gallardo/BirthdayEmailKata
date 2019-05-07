package xpug.kata.birthday_greetings;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeRepository {

	List<Employee> getAllEmployees() throws ParseException, IOException;

}

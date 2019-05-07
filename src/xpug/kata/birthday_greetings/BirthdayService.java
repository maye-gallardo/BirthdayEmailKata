package xpug.kata.birthday_greetings;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {
	private EmployeeRepository repository;
	private EmailService mail;
	
	public BirthdayService(EmployeeRepository repository, EmailService mail) {
		this.repository = repository;
		this.mail = mail;
	}
	

	public void sendGreetings(OurDate ourDate) throws IOException, ParseException, AddressException, MessagingException {
		List<Employee> employees = repository.getAllEmployees();
		for(Employee employee : employees) {
			if (employee.isBirthday(ourDate)) {
				mail.sendMessage("sender@here.com", employee);
			}
		}
		
	}

	public static void main(String[] args) {
		EmployeeRepository repository = new FileEmployeeRepository("employee_data.txt");
		EmailService mail = new SMTPMailService("localhost", 25); 
		BirthdayService service = new BirthdayService(repository, mail);
		try {
			service.sendGreetings(new OurDate("2008/10/08"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

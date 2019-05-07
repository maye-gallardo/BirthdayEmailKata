package xpug.kata.birthday_greetings;

import static spark.Spark.*;

import com.dumbster.smtp.SimpleSmtpServer;

public class Main {
	public static void main(String[] args) {
		get("/", (request, response) -> sendGreetingsView());
		post("/sendGreetings", (request, response) -> {
			SimpleSmtpServer server;
			server = SimpleSmtpServer.start(1081);
			EmployeeRepository repository = new FileEmployeeRepository("employee_data.txt");
			EmailService mail = new SMTPMailService("localhost", 1081);
			BirthdayService service = new BirthdayService(repository, mail);
			service.sendGreetings(new OurDate("2008/10/08"));
			server.stop();
			return "<h2>Emails sent: "+service.quantityOfGreetingsSent()+"</h2>";
		});
	}

	private static String sendGreetingsView() {
		return "<html>"
				+ "<body>"
				+ 	 "<form method='post' action='/sendGreetings'>" 
				+	 	"<div><label>From:</label>"
				+ 		"<input type='text' name='from'></div>"
				+	 	"<div><label>Subject:</label>"
				+ 		"<input type='text' name='subject'></div>"
				+	 	"<div><label>Body:</label>"
				+ 		"<input type='text' name='body'></div>"
				+ 		"<div><input type='submit' value='Send To All'></div>"
				+		"</form>"
				+ "</body>"
				+ "</html>";
	}
}

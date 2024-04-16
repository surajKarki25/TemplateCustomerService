package com.example.CustomerServicedemo;

import com.example.Customers.CustomerCare;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class CustomerServicedemoApplication {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Our Customer Care Application ");
		System.out.print("Please enter your name: ");
		String name = scanner.nextLine();
		System.out.println("Thanks For reaching to us " + name);

		while (true) {
			System.out.println("Please select A department to connect to: \n1. Payment Department " +
					"\n2. Query Department\n3. Sales Department\n4. Exit");
			int userChoice = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			String deptChoice;
			switch (userChoice) {
				case 1 -> deptChoice = "paymentDepartment";
				case 2 -> deptChoice = "queryDepartment";
				case 3 -> deptChoice = "salesDepartment";
				case 4 -> {
					System.out.println("Exiting...");
					context.close();
					return;
				}
				default -> {
					System.out.println("Invalid choice");
					continue; // Continue to the next iteration of the loop
				}
			}

			// Get the department bean from the context
			CustomerCare department = (CustomerCare) context.getBean(deptChoice);

			// Set customer name
			department.setCustomerName(name);

			// Call the service method of the selected department
			department.getService();

			// Prompt user for message
			System.out.print("Please enter your message: ");
			String message = scanner.nextLine();

			// Set problem/message in the selected department
			department.setProblem(message);
			department.getProblem();

			// Consume newline character
			scanner.nextLine();
		}
	}
}

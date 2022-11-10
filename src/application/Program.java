package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("ENTER FULL FILE PATH: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			
			while(line != null){
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("ENTER SALARY: U$");
			double salary = sc.nextDouble();
			
			List<String> emails = list.stream()
					.filter(s -> s.getSalary() > salary)
					.map(s -> s.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("EMAIL OF PEOPLE WHOSE SALARY IS MORE THAN " +String.format("U$%.2f", salary) + ": ");
			emails.forEach(System.out::println);
			
			double sum = list.stream()
					.filter(s -> s.getName().charAt(0) == 'M')
					.map(s -> s.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.println("SUM OF SALARY FROM PEOPLE WHOSE NAME STARTS WITH 'M': " + String.format("U$%.2f", sum));
			
		}catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
		sc.close();

	}

}

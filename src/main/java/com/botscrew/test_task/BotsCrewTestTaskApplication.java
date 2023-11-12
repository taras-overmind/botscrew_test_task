package com.botscrew.test_task;

import com.botscrew.test_task.repository.DepartmentRepository;
import com.botscrew.test_task.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BotsCrewTestTaskApplication implements CommandLineRunner {
    @Autowired
    private CommandService commandService;

    public static void main(String[] args) {
        SpringApplication.run(BotsCrewTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                processCommand(scanner.nextLine());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("The end!");
        }
    }

    private void processCommand(String command) throws InterruptedException {
        String departmentName;
        String pattern1 = "^Who is head of department (.+)$";
        String pattern2 = "^Show (.+) statistics$";
        String pattern3 = "^Show the average salary for the department (.+)$";
        String pattern4 = "^Show count of employee for (.+)$";
        String pattern5 = "^Global search by (.+)$";
        if (command.matches(pattern1)) {
            departmentName = extractDepartmentName(command, pattern1);
            System.out.println(commandService.getDepartmentHead(departmentName));
        } else if (command.matches(pattern2)) {
            departmentName = extractDepartmentName(command, pattern2);
            System.out.println(commandService.showDepartmentStatistics(departmentName));
        } else if (command.matches(pattern3)) {
            departmentName = extractDepartmentName(command, pattern3);
            System.out.println(commandService.showAverageSalary(departmentName));
        } else if (command.matches(pattern4)) {
            departmentName = extractDepartmentName(command, pattern4);
            System.out.println(commandService.showCountOfEmployees(departmentName));
        } else if (command.matches(pattern5)) {
            String template = extractDepartmentName(command, pattern5);
            System.out.println(commandService.globalSearch(template.toLowerCase()));
        } else if (command.equals("end")) {
            throw new InterruptedException();
        } else {
            System.out.println("Wrong command");
        }
    }

    private static String extractDepartmentName(String command, String regex) {
        return command.replaceAll(regex, "$1");
    }

}

package com.botscrew.test_task.service;

import com.botscrew.test_task.model.Department;
import com.botscrew.test_task.model.Lector;
import com.botscrew.test_task.repository.DepartmentRepository;
import com.botscrew.test_task.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandService {
    DepartmentRepository departmentRepository;
    LectorRepository lectorRepository;

    @Autowired
    public CommandService(DepartmentRepository departmentRepository, LectorRepository lectorRepository) {
        this.departmentRepository = departmentRepository;
        this.lectorRepository = lectorRepository;
    }

    public String showDepartmentStatistics(String department_name) {
        StringBuilder result = new StringBuilder();
        String assistant_count = departmentRepository
                .countLectorDegreesByDepartmentNameAndDegree(department_name, "ASSISTANT");
        result.append("assistants - ").append(assistant_count == null ? 0 : assistant_count).append("\n");
        String associate_professor_count = departmentRepository
                .countLectorDegreesByDepartmentNameAndDegree(department_name, "ASSOCIATE_PROFESSOR");
        result.append("associate professors - ").append(associate_professor_count == null ? 0 : associate_professor_count).append("\n");
        String professors_count = departmentRepository
                .countLectorDegreesByDepartmentNameAndDegree(department_name, "PROFESSOR");
        result.append("professor - ").append(professors_count == null ? 0 : professors_count);
        return result.toString();
    }

    public String getDepartmentHead(String department_name) {
        if (departmentRepository.findDepartmentByName(department_name) != null)
            return "Head of " + department_name + " department is " + departmentRepository.getHeadOfDepartmentByDepartmentName(department_name);
        else
            return "No such department";
    }

    public String showAverageSalary(String department_name) {
        if (departmentRepository.getLectorNamesByDepartmentName(department_name).isEmpty()) {
            return "No employees";
        } else if (departmentRepository.findDepartmentByName(department_name) != null)
            return "The average salary of " + department_name + " is " + departmentRepository.getAverageSalaryByDepartmentName(department_name);
        else
            return "No such department";
    }

    public String showCountOfEmployees(String department_name) {
        if (departmentRepository.getLectorNamesByDepartmentName(department_name).isEmpty())
            return "No employees";
        else if (departmentRepository.findDepartmentByName(department_name) != null)
            return departmentRepository.countEmployeesByDepartmentName(department_name);
        else
            return "No such department";
    }

    @Transactional
    public String globalSearch(String template) {
        StringBuilder searchResult = new StringBuilder();
        var lectors = lectorRepository.findAll().stream().map(Lector::getName)
                .filter(x -> x.toLowerCase().contains(template)).toList();
        searchResult.append("Lectors: ");
        appendResults(lectors, searchResult);
        searchResult.append("\n");
        var heads_of_departments = departmentRepository.findAll().stream().map(Department::getHead_of_department_name)
                .filter(x -> x.toLowerCase().contains(template)).toList();
        searchResult.append("Heads of department: ");
        appendResults(heads_of_departments, searchResult);
        searchResult.append("\n");
        var departments = departmentRepository.findAll().stream().map(Department::getName)
                .filter(x -> x.toLowerCase().contains(template)).toList();
        searchResult.append("Departments: ");
        appendResults(departments, searchResult);
        return searchResult.toString();
    }

    private static void appendResults(List<String> list, StringBuilder sb) {
        if (!list.isEmpty()) {
            for (var el : list)
                sb.append(el).append(", ");
            sb.delete(sb.length() - 2, sb.length());
        }

    }


}

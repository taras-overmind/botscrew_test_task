package com.botscrew.test_task.repository;

import com.botscrew.test_task.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentByName(String name);

    @Query(value = "select department.head_of_department_name\n" +
            "from department\n" +
            "where department.name=?1", nativeQuery = true)
    String getHeadOfDepartmentByDepartmentName(String department_name);

    @Query(value = "select  count(l.degree)\n" +
            "from department\n" +
            "join public.lector_department ld on department.id = ld.department_id\n" +
            "join public.lector l on l.id = ld.lector_id\n" +
            "where department.name=?1 and l.degree=?2\n" +
            "GROUP BY l.degree", nativeQuery = true)
    String countLectorDegreesByDepartmentNameAndDegree(String department_name, String degree);

    @Query(value = "select avg(l.salary)\n" +
            "from department\n" +
            "join public.lector_department ld on department.id = ld.department_id\n" +
            "join public.lector l on l.id = ld.lector_id\n" +
            "where department.name=?1\n" +
            "GROUP BY department.name", nativeQuery = true)
    String getAverageSalaryByDepartmentName(String department_name);

    @Query(value = "select count(l)\n" +
            "from department\n" +
            "join public.lector_department ld on department.id = ld.department_id\n" +
            "join public.lector l on l.id = ld.lector_id\n" +
            "where department.name=?1\n" +
            "GROUP BY department.name", nativeQuery = true)
    String countEmployeesByDepartmentName(String department_name);

    @Query(value = "Select lector.name from lector\n" +
            "join public.lector_department ld on lector.id = ld.lector_id\n" +
            "join public.department d on d.id = ld.department_id\n" +
            "WHERE d.name=?1", nativeQuery = true)
    List<String> getLectorNamesByDepartmentName(String department_name);

    @Query(value = "select head_of_department_name from department", nativeQuery = true)
    List<String> getHeadOfDepartmentsNames();

    @Query(value = "select department.name from department", nativeQuery = true)
    List<String> getDepartmentNames();
}

package com.botscrew.test_task.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String head_of_department_name;
    @ManyToMany
    @JoinTable(
            name = "Lector_Department",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name="lector_id"))
    private List<Lector> lectors;

    public Department(){}
    public Department(String name, String head_of_department_name) {
        this.name = name;
        this.head_of_department_name = head_of_department_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_of_department_name() {
        return head_of_department_name;
    }

    public void setHead_of_department_name(String head_of_department_name) {
        this.head_of_department_name = head_of_department_name;
    }

    public List<Lector> getEmployees() {
        return lectors;
    }

    public void setLectors(List<Lector> lectors) {
        this.lectors = lectors;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", head_of_department_name='" + head_of_department_name + '\'' +
                '}';
    }
}

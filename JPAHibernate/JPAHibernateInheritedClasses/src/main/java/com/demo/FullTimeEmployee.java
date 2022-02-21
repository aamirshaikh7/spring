package com.demo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity(name = "FullTimeEmployees")
public class FullTimeEmployee extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, Integer salary) {
        super(name);
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", salary=" + salary +
                '}';
    }
}

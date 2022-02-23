package com.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("InheritedClassesDBUnit");

        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

//            Department it = new Department("IT");
//
//            Employee aamir = new Employee("Aamir");
//            it.addEmployee(aamir);
//
//            FullTimeEmployee john = new FullTimeEmployee("John", 9999);
//            john.setDepartment(it);
//
//            ContractEmployee jane = new ContractEmployee("Jane", 20);
//            jane.setDepartment(it);
//
//            entityManager.persist(john);
//            entityManager.persist(jane);
//
//            entityManager.persist(it);

            Department getIT = entityManager.find(Department.class, 1);

            System.out.println("Department");
            System.out.println(getIT);

            Employee getAamir = entityManager.find(Employee.class, 3);

            System.out.println("Employee");
            System.out.println(getAamir);

            Employee getJohn = entityManager.find(FullTimeEmployee.class, 1);

            System.out.println("FullTimeEmployee");
            System.out.println(getJohn);

            Employee getJane = entityManager.find(ContractEmployee.class, 2);

            System.out.println("ContractEmployee");
            System.out.println(getJane);

            List<Employee> employees = (List<Employee>) entityManager.createQuery("from Employees", Employee.class).getResultList();

            System.out.println("All Employees");
            for (Employee e: employees) {
                System.out.println(e);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.getTransaction().commit();

            entityManager.close();
            factory.close();
        }
    }
}

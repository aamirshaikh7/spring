package com.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalTime;

public class App
{
    public static void main( String[] args )
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("EmployeeDBUnit");
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Employee employee1 = new Employee("xyz", "abc", "Trainee", 49999D);
            Employee employee2 = new Employee("lmn", "opq", "Trainee", 59999D);

            entityManager.persist(employee1);
            entityManager.persist(employee2);

            Department department1 = new Department("IT", "abc");
            Department department2 = new Department("HR", "xyz");

            entityManager.persist(department1);
            entityManager.persist(department2);

            EmployeeCheckin checkin1 = new EmployeeCheckin(1, LocalDate.now(), LocalTime.now());
            EmployeeCheckin checkin2 = new EmployeeCheckin(1, LocalDate.now().plusDays(1), LocalTime.now());

            System.out.println(checkin1.toString());
            System.out.println(checkin2.toString());

            entityManager.persist(checkin1);
            entityManager.persist(checkin2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.getTransaction().commit();

            entityManager.close();
            factory.close();
        }
    }
}

package com.demo;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("unchecked")
    public static void main( String[] args )
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("QueriesDBUnit");

        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

//          Aggregates

//          Count of Categories

            TypedQuery<Long> categoryCount = entityManager.createQuery("SELECT COUNT(c) FROM Categories c", Long.class);

            System.out.println("Category count");
            System.out.println(categoryCount.getSingleResult());

//          Products average

            TypedQuery<Double> productPriceAverage = entityManager.createQuery("SELECT AVG(p.price) FROM Products p", Double.class);

            System.out.println("Products average");
            System.out.println(productPriceAverage.getSingleResult());

//          Average price for each category

            Query averagePriceForEachCategory = entityManager.createQuery(
                    "SELECT c.name, AVG(p.price) FROM Categories c " +
                            "INNER JOIN c.products p GROUP BY c.name");

            List<Object[]> averagePriceForEachCategoryList = averagePriceForEachCategory.getResultList();

            System.out.println("Average price for each category");
            averagePriceForEachCategoryList.forEach(result -> System.out.println(Arrays.toString(result)));

//          Maximum price for each category greater than a value

            Query maxPriceForEachCategory = entityManager.createQuery(
                    "SELECT c.name, MAX(p.price) FROM Categories c " +
                            "INNER JOIN c.products p GROUP BY c.name " +
                            "HAVING MAX(p.price) > ?1");

            maxPriceForEachCategory.setParameter(1, 34000F);

            List<Object[]> maxPriceForEachCategoryList = maxPriceForEachCategory.getResultList();

            System.out.println("Maximum price for each category greater than a value");
            maxPriceForEachCategoryList.forEach(result -> System.out.println(Arrays.toString(result)));

//          Exists clause and sub query

            Query existsAndSubQuery = entityManager.createQuery(
                    "SELECT c.name, c.id FROM Categories c " +
                            "WHERE EXISTS " +
                            "(SELECT p FROM Products p WHERE p.price > ?1 AND p.category.id = c.id)");

            existsAndSubQuery.setParameter(1, 34000F);

            List<Object[]> existsAndSubQueryList = existsAndSubQuery.getResultList();

            System.out.println("Exists clause and sub query");
            existsAndSubQueryList.forEach(result -> System.out.println(Arrays.toString(result)));

//          Case and statements

            Query caseQuery = entityManager.createQuery(
                    "SELECT p.name, p.price, " +
                            "CASE p.category.id " +
                            "WHEN 1 THEN 'Electronics' " +
                            "WHEN 2 THEN 'Fashion' " +
                            "ELSE p.category.name END " +
                            "FROM Products p");

            List<Object[]> caseQueryList = caseQuery.getResultList();

            System.out.println("Case and statements");
            caseQueryList.forEach(result -> System.out.println(Arrays.toString(result)));
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.getTransaction().commit();

            entityManager.close();
            factory.close();
        }
    }
}

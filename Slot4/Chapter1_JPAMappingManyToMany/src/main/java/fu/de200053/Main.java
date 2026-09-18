package fu.de200053;

import fu.de200053.pojo.Employee;
import fu.de200053.pojo.Gender;
import fu.de200053.pojo.Project;
import fu.de200053.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            // =========================
            // TODO 5.7 - Create 3 Employees
            // =========================

            Employee employee1 = new Employee(
                    "employee1@gmail.com",
                    "Nguyen Lam Thi",
                    Gender.MALE,
                    new BigDecimal("1500.00"),
                    LocalDate.of(2022, 1, 10)
            );

            Employee employee2 = new Employee(
                    "employee2@gmail.com",
                    "Duong Trong",
                    Gender.FEMALE,
                    new BigDecimal("1800.00"),
                    LocalDate.of(2023, 3, 15)
            );

            Employee employee3 = new Employee(
                    "employee3@gmail.com",
                    "Ngo Viet Bao Huy",
                    Gender.OTHER,
                    new BigDecimal("2000.00"),
                    LocalDate.of(2024, 5, 20)
            );

            employee1.setActive(true);
            employee2.setActive(true);
            employee3.setActive(true);

            // =========================
            // TODO 5.7 - Create 2 Projects
            // =========================

            Project projectA = new Project(
                    "PRJ-A",
                    "Project A",
                    new BigDecimal("50000.00"),
                    LocalDate.of(2025, 1, 1),
                    null
            );

            Project projectB = new Project(
                    "PRJ-B",
                    "Project B",
                    new BigDecimal("70000.00"),
                    LocalDate.of(2025, 2, 1),
                    null
            );

            // =========================
            // Save Employees and Projects
            // =========================

            em.persist(employee1);
            em.persist(employee2);
            em.persist(employee3);

            em.persist(projectA);
            em.persist(projectB);

            // =========================
            // Cross-assign Employees
            // =========================

            // Employee 1 -> Project A + Project B
            employee1.assignToProject(projectA);
            employee1.assignToProject(projectB);

            // Employee 2 -> Project B
            employee2.assignToProject(projectB);

            // Employee 3 -> Project A
            employee3.assignToProject(projectA);

            em.getTransaction().commit();

            // =========================
            // Print projects of each Employee
            // =========================

            System.out.println("Employee 1: " + employee1.getFullName());
            for (Project project : employee1.getProjects()) {
                System.out.println("  - " + project.getProjectName());
            }

            System.out.println("Employee 2: " + employee2.getFullName());
            for (Project project : employee2.getProjects()) {
                System.out.println("  - " + project.getProjectName());
            }

            System.out.println("Employee 3: " + employee3.getFullName());
            for (Project project : employee3.getProjects()) {
                System.out.println("  - " + project.getProjectName());
            }

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }
}
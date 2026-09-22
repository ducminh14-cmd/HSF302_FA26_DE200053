package fu.de200053;

import fu.de200053.dao.EmployeeDAO;
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

                    // Employee id = 1
                    Employee employee = em.find(Employee.class, 1L);

                    // Project A id = 1
                    Project project = em.find(Project.class, 1L);

                    // TODO 5.9: Unassign Employee 1 from Project A
                    employee.unassignFromProject(project);

                    em.getTransaction().commit();

                    System.out.println("Đã gỡ Employee khỏi Project.");

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



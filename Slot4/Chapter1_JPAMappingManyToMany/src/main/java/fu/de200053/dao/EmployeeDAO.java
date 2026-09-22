package fu.de200053.dao;

import fu.de200053.pojo.Employee;
import fu.de200053.pojo.Project;
import fu.de200053.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class EmployeeDAO {

    public void assignEmployeeToProject(Long employeeId, Long projectId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Employee employee = em.find(Employee.class, employeeId);
            Project project = em.find(Project.class, projectId);

            employee.assignToProject(project);

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void printProjectStatistics() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            String jpql = """
                SELECT p.projectName, COUNT(e), SUM(e.salary)
                FROM Project p JOIN p.employees e
                WHERE e.active = true
                GROUP BY p.projectName
                """;

            var query = em.createQuery(jpql);

            var results = query.getResultList();

            for (Object result : results) {
                Object[] row = (Object[]) result;

                System.out.println("Project: " + row[0]);
                System.out.println("Employee count: " + row[1]);
                System.out.println("Total salary: " + row[2]);
                System.out.println("--------------------");
            }

        } finally {
            em.close();
        }
    }
}

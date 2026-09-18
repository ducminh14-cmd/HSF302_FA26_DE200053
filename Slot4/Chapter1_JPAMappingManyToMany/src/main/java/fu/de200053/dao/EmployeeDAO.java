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
}

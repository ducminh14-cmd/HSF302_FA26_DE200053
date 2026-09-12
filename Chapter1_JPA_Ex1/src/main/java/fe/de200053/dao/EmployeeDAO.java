package fe.de200053.dao;

import fe.de200053.pojo.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class EmployeeDAO {

    private final EntityManagerFactory emf;

    public EmployeeDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public void save(Employee e) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(e);

            em.getTransaction().commit();

        } catch (RuntimeException ex) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw ex;

        } finally {
            em.close();
        }
    }
     // TODO 4

    public Employee findById(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Employee.class, id);

        } finally {
            em.close();
        }
    }

    public List<Employee> findAll() {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Employee> query =
                    em.createQuery(
                            "SELECT e FROM Employee e",
                            Employee.class
                    );

            return query.getResultList();

        } finally {
            em.close();
        }
    }


}

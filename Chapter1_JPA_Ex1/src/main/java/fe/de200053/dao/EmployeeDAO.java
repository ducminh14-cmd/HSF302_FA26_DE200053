package fe.de200053.dao;

import fe.de200053.pojo.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.math.BigDecimal;

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

    // TODO 0.5 - FIND BY EMAIL
    public Employee findByEmail(String email) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Employee> query =
                    em.createQuery(
                            "SELECT e FROM Employee e WHERE e.email = :email",
                            Employee.class
                    );

            query.setParameter("email", email);

            List<Employee> employees = query.getResultList();

            if (employees.isEmpty()) {
                return null;
            }

            return employees.get(0);

        } finally {
            em.close();
        }
    }

    // TODO 0.5 - FIND BY MINIMUM SALARY
    public List<Employee> findBySalaryGreaterThan(BigDecimal salary) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Employee> query =
                    em.createQuery(
                            "SELECT e FROM Employee e WHERE e.salary > :salary",
                            Employee.class
                    );

            query.setParameter("salary", salary);

            return query.getResultList();

        } finally {
            em.close();
        }
    }


}

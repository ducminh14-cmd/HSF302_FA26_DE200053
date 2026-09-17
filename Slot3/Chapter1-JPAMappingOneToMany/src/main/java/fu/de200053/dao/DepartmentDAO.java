package fu.de200053.dao;

import fu.de200053.pojo.Department;
import fu.de200053.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class DepartmentDAO {

    // CREATE
    public void save(Department department) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(department);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // READ ALL
    public List<Department> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            List<Department> departments = em.createQuery(
                    "SELECT d FROM Department d",
                    Department.class
            ).getResultList();

            // TODO 2.8 - Trigger N+1
            for (Department d : departments) {
                d.getEmployees().size();
            }

            return departments;

        } finally {
            em.close();
        }
    }




    // READ BY ID
    public Department findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Department.class, id);

        } finally {
            em.close();
        }
    }

    // UPDATE
    public Department update(Department department) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            department = em.merge(department);

            tx.commit();

            return department;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // DELETE
    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Department department = em.find(Department.class, id);

            if (department != null) {
                em.remove(department);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // FIND BY ID WITH EMPLOYEES
    public Department findByIdWithEmployees(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT d FROM Department d JOIN FETCH d.employees WHERE d.id = :id",
                            Department.class
                    )
                    .setParameter("id", id)
                    .getSingleResult();

        } finally {
            em.close();
        }
    }


}
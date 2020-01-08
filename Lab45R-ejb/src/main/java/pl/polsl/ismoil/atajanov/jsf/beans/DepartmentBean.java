package pl.polsl.ismoil.atajanov.jsf.beans;

import pl.polsl.ismoil.atajanov.jsf.model.Department;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 * A bean for CRUD operations on Department table
 * @author Ismoil Atajanov
 * @version 1.0
 */
@Stateless
@LocalBean
public class DepartmentBean { 

     /**
     * Entity manager used perform operations
     */
    @PersistenceContext
    private EntityManager em;

    
    /**
     * Method creating or updating a department if it already exists
     * @param department
     * @return 
     */
    public Department createOrUpdate(Department department) {
        if (department.getId() == null) {
            em.persist(department);
        } else {
            em.merge(department);
        }
        return department;
    }

    
    /**
     * Finding a department with a given id
     * @param id id of the department to look for
     * @return department object
     */
    public Department findById(Integer id) {
        return em.find(Department.class, id);
    }

    /**
     * Deleting department from the table
     * @param id id of the department to delete
     */
    public void remove(Integer id) {
        Department department = findById(id);

        if (department != null) {
            em.remove(department);
        }
    }

    /**
     * Finding all departments in the table
     * @return list of departments 
     */
    public List<Department> findAllDepartments() {
        return em.createNamedQuery(Department.FIND_ALL, 
                Department.class).getResultList();
    }
}


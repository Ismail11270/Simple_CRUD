package pl.polsl.ismoil.atajanov.jsf.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.polsl.ismoil.atajanov.jsf.model.Employee;

/**
 * A bean for CRUD operations on Employee table
 *
 * @author Ismoil Atajanov
 */
@Stateless
@LocalBean
public class EmployeeBean {

    /**
     * Entity manager used perform operations
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Method creating or updating an employee if it already exists
     * @param employee
     * @return 
     */
    public Employee createOrUpdateEmployee(Employee employee) {
        if (employee.getId() == null) {
            em.persist(employee);
        } else {
            em.merge(employee);
        }
        return employee;
    }

    /**
     * Finding an employee with a given id
     * @param id id of the employee to look for
     * @return employee object
     */
    public Employee findEmployeeById(Integer id) {
        return em.find(Employee.class, id);
    }

    /**
     * Deleting employee from the table
     * @param id id of the employee to delete
     */
    public void removeEmployee(Integer id) {
        Employee employee = findEmployeeById(id);

        if (employee != null) {
            em.remove(employee);
        }
    }

    /**
     * Finding all employees in the table
     * @return list of employees
     */
    public List<Employee> findAllEmployees() {
        return em.createNamedQuery(Employee.FIND_ALL,
                Employee.class).getResultList();
    }
}

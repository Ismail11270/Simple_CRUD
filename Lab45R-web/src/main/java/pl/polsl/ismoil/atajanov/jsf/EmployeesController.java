package pl.polsl.ismoil.atajanov.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.polsl.ismoil.atajanov.jsf.beans.EmployeeBean;
import pl.polsl.ismoil.atajanov.jsf.model.Employee;

/**
 * Controller class having control over all the employees
 * @author Ismoil Atajanov
 * @version 1.0
 */
@ViewScoped
@ManagedBean
public class EmployeesController {

    /**
     * EmployeeBean object that allows to perform CRUD operations on employee table
     */
    @EJB
    private EmployeeBean employeeBean;
    
    /**
     * Get all employees
     * @return list of employees
     */
    public List<Employee> getEmployees(){
        return employeeBean.findAllEmployees();
    }
    
    /**
     * Updating an employee
     * @param employee employee to modify
     * @return an action string
     */
    public String actionEdit(Employee employee){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("employee",employee);
        return "edit-emp";
    }
    
    /**
     * Action listener removing employees
     * @param employee an employee to remove
     */
    public void actionListenerRemove(Employee employee){
        employeeBean.removeEmployee(employee.getId());
    }
}

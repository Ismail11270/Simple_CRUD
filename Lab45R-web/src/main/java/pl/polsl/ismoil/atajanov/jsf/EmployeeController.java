package pl.polsl.ismoil.atajanov.jsf;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.polsl.ismoil.atajanov.jsf.beans.DepartmentBean;
import pl.polsl.ismoil.atajanov.jsf.beans.EmployeeBean;
import pl.polsl.ismoil.atajanov.jsf.model.Employee;

/**
 * Controller class, controlling only a current employee
 * @author Ismoil Atajanov
 * @version 1.0
 */
@ViewScoped
@ManagedBean
public class EmployeeController {

    
    /**
     * Employee object
     */
    private Employee employee;
    
    /**
     * EmployeeBean object that allows to perform CRUD operations on employee table
     */
    @EJB
    private EmployeeBean employeeBean;
    @EJB
    private DepartmentBean departmentBean;
    
    /**
     * Post construct method 
     * checking if the employee is null, if yes it's assigned with a new object
     */
    @PostConstruct
    public void init(){
        employee = (Employee)FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().remove("employee");
        if(employee == null){
            employee = new Employee();
        }
    }
    
    /**
     * Employee field getter
     * @return employee object
     */
    public Employee getEmployee(){
        return employee;
    }
    
    public void setDepartment(Integer id){
        employee.setDepartment(departmentBean.findDepartmentById(id));
    }
    
    public Integer getDepartment(){
        return employee.getDepartment()==null ? null : employee.getDepartment().getId();
    }
    
    /**
     * Save the employee
     * @return action string
     */
    public String actionSave(){
        employeeBean.createOrUpdateEmployee(employee);
        return "list-emp";
    }
    
}

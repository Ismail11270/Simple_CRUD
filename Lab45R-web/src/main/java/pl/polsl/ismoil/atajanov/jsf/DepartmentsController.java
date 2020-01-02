package pl.polsl.ismoil.atajanov.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.polsl.ismoil.atajanov.jsf.beans.DepartmentBean;
import pl.polsl.ismoil.atajanov.jsf.model.Department;


/**
 * Controller class having control over all the departments
 * @author Ismoil Atajanov
 * @version 1.0
 */
@ViewScoped
@ManagedBean
public class DepartmentsController {

    /**
     * EmployeeBean object that allows to perform CRUD operations on employee table
     */
    @EJB
    private DepartmentBean departmentBean;
    
    
    /**
     * Get all departments
     * @return a list of departments
     */
    public List<Department> getDepartments(){
        return departmentBean.findAllDepartment();
    }
    
    /**
     * Edit a department
     * @param department the object to modify
     * @return an action string
     */
    public String actionEdit(Department department){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("department",department);
        return "edit-dep";
    }
    
    /**
     * Action listener removing a department
     * @param department object to remove
     */
    public void actionListenerRemove(Department department){
        departmentBean.removeDepartment(department.getId());
    }
}

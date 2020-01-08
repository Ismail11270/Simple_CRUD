package pl.polsl.ismoil.atajanov.jsf;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.polsl.ismoil.atajanov.jsf.beans.DepartmentBean;
import pl.polsl.ismoil.atajanov.jsf.model.Department;

/**
 * Controller class, controlling only a current employee
 * @author Ismoil Atajanov
 * @version 1.0
 */
@ViewScoped
@ManagedBean
public class DepartmentController {

    
    /**
     * Department object
     */
    private Department department;
    
    /**
     * DepartmentBean object that allows to perfrom CRUD operations on Department table
     */
    @EJB
    private DepartmentBean departmentBean;
    
    /**
     * Post construct method 
     * checking if the Department is null, if yes it's assigned with a new object
     */
    @PostConstruct
    public void init(){
        department = (Department)FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().remove("department");
        if(department == null){
            department = new Department();
        }
    }
    
    /**
     * department field getter
     * @return department object
     */
    public Department getDepartment(){
        return department;
    }
    
    
    /**
     * Save the department
     * @return action string
     */
    public String actionSave(){
        departmentBean.createOrUpdate(department);
        return "list-dep";
    }
    
}

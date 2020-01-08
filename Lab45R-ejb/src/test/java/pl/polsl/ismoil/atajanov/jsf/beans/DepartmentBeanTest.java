/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jsf.beans;

import pl.polsl.ismoil.atajanov.jsf.model.Employee;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.polsl.ismoil.atajanov.jsf.model.Department;

/**
 * Test class for DepartmentBean bean
 *
 * @author Ismoil Atajanov
 * @version 1.0
 */
public class DepartmentBeanTest {

    private static EJBContainer container;
    private static DepartmentBean departmentBean;

    @BeforeClass
    public static void init() throws NamingException {
        Properties properties = new Properties();

        properties.put("xyz", "new://Resource?type=DataSource");
        properties.put("xyz.UserName", "root");
        properties.put("xyz.Password", "Ismail_05-09");
        properties.put("xyz.JdbcUrl",
                "jdbc:mysql://localhost:3306/lab_db");
        properties.put("xyz.JdbcDriver", "com.mysql.cj.jdbc.Driver");
        properties.put("xyz.JtaManaged", "true");
        properties.put("xyz.ConnectionProperties",
                "useSSL=false;allowPublicKeyRetrieval=true");
        container = EJBContainer.createEJBContainer(properties);
        departmentBean = (DepartmentBean) container.getContext().lookup("java:global/Lab45R-ejb/DepartmentBean");

        try {
            departmentBean.findAllDepartments();
        } catch (Exception e) {

        }
    }

    @Test
    public void testCreateAndDelete() {
        Department department = new Department("Department one", "Street streetowska");
        assertNull("Must be null", department.getId());
        departmentBean.createOrUpdate(department);
        assertNotNull("Must be non null", department.getId());
        int id = department.getId();
        
        //test delete
        departmentBean.remove(id);
        Department dep = departmentBean.findById(id); 
        assertNull("Deleting failed",dep);
    }

    @Test
    public void testFindAll(){
        List<Department> findAll = departmentBean.findAllDepartments();
        assertNotNull("Find all test failed, list is null", findAll);
    }
  

    @AfterClass
    public static void end() {
        container.close();
    }
   
}

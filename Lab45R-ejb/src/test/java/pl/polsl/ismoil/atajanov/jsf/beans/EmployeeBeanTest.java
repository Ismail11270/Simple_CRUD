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

/**
 * Test class for EmployeeBean bean
 *
 * @author Ismoil Atajanov
 * @version 1.0
 */
public class EmployeeBeanTest {

    private static EJBContainer container;
    private static EmployeeBean employeeBean;

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
        employeeBean = (EmployeeBean) container.getContext().lookup("java:global/Lab45R-ejb/EmployeeBean");

        try {
            employeeBean.findAllEmployees();
        } catch (Exception e) {

        }
    }

    @Test
    public void testCreateAndDelete() {
        Employee employee = new Employee("Ismoil", new Date(), null);
        assertNull("Must be null", employee.getId());
        employeeBean.createOrUpdate(employee);
        assertNotNull("Must be non null", employee.getId());
        int id = employee.getId();
        
        //test delete
        employeeBean.remove(id);
        Employee emp = employeeBean.findById(id); 
        assertNull("Deleting failed",emp);
    }

    @Test
    public void testFindAll(){
        List<Employee> findAll = employeeBean.findAllEmployees();
        assertNotNull("Find all test failed, list is null", findAll);
    }
  

    @AfterClass
    public static void end() {
        container.close();
    }
   
}

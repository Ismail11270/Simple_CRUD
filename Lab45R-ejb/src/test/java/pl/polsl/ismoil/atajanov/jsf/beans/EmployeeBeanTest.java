/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jsf.beans;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.polsl.ismoil.atajanov.jsf.model.Employee;

/**
 * Test class for EmployeeBean bean
 *
 * @author Ismoil Atajanov
 * @version 1.0
 */
public class EmployeeBeanTest {

    private static EJBContainer container;
    private static EmployeeBean bean;

    @BeforeClass
    public static void init() throws NamingException {
        Properties properties = new Properties();

        properties.put("abc", "new://Resource?type=DataSource");
        properties.put("abc.UserName", "root");
        properties.put("abc.Password", "Ismail_05-09");
        properties.put("abc.JdbcUrl",
                "jdbc:mysql://localhost:3306/lab_db");
        properties.put("abc.JdbcDriver", "com.mysql.cj.jdbc.Driver");
        properties.put("abc.JtaManaged", "true");
        properties.put("abc.ConnectionProperties",
                "useSSL=false;allowPublicKeyRetrieval=true");
        container = EJBContainer.createEJBContainer(properties);
        bean = (EmployeeBean) container.getContext().lookup("java:global/Lab45R-ejb/EmployeeBean");
    }

    @AfterClass
    public static void end() {
        container.close();
    }

    @Test
    public void testCreate() {
        Employee employee = new Employee("Ismoil", new Date(), null);
        assertNull("Must be null", employee.getId());
//       bean.createOrUpdateEmployee(employee);
//        employee = bean.findEmployeeById(1);
        List<Employee> findAllEmployees = bean.findAllEmployees();
        assertNotNull("Must be non null", employee.getId());
    }
}

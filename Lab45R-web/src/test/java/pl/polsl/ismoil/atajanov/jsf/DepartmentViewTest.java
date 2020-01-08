package pl.polsl.ismoil.atajanov.jsf;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class for testing the view
 *
 * @author Ismoil Atajanov
 * @version 1.0
 */
public class DepartmentViewTest {

    /**
     * Test method for testing the view
     */
    @Test
    public void testView(){
        try (WebClient webClient = new WebClient()) {
            // access the page you want to test
            HtmlPage page = webClient.getPage("http://localhost:8080/Lab45R-web/faces/departments.xhtml");
            // make sure the page is accessed by checking its title
            assertEquals("Should be CRUD Employees, Departments", "Departments list", page.getTitleText());

            // get the form and check whether it exists
            HtmlForm form = page.getFormByName("dep-list-form");
            assertNotNull("Should be non-null", form);

            HtmlButton button = page.getHtmlElementById("dep-list:new-btn");
            button.click();
            Thread.sleep(3000);

            //departments-edit.xhtml
            page = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
            assertEquals("Should be Department edit", "Department edit", page.getTitleText());

            form = page.getFormByName("dep-edit-form");
            assertNotNull("Edit form null", form);
//            button = page.getHtmlElementById("save_btn");   //didn't work
            List<HtmlButton> buttons = form.getByXPath(".//button");
            assertFalse("Should not be empty", buttons.isEmpty());
            button = buttons.get(0);
            assertNotNull("save button null", button);

            //test validation, submit empty form
            button.click();
            Thread.sleep(3000);
            //if a validation error occured the page should remain the same
            page = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
            assertEquals("Should be Department edit", "Department edit", page.getTitleText());
            //search for the message 
            assertTrue("Validation error for the address input expected ", page.asText().contains("Address: Validation Error: Value is required."));
            assertTrue("Validation error for the name input expected ", page.asText().contains("Name: Validation Error: Value is required."));
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}

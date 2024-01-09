package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.forms.FooterForm;

public class ProjectsPage extends Form {

    private final FooterForm footerForm = new FooterForm();

    public ProjectsPage() {
        super(By.xpath("//a[@href='/web/projects']"), "Projects page");
    }

    public FooterForm footerForm() {
        return footerForm;
    }
}

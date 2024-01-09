package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.forms.FooterForm;

public class ProjectsPage extends Form {

    private final static String PROJECT_XPATH = "//a[text()='%s']";
    private final FooterForm footerForm = new FooterForm();

    public ProjectsPage() {
        super(By.xpath("//a[@href='/web/projects']"), "Projects page");
    }

    public FooterForm footerForm() {
        return footerForm;
    }

    private ILink getProjectLink(String projectName) {
        return getElementFactory().getLink(By.xpath(String.format(PROJECT_XPATH, projectName)), "Project link");
    }

    public void clickProjectLink(String projectName) {
        ILink projectLink = getProjectLink(projectName);
        projectLink.clickAndWait();
    }

    public String getProjectId(String projectName) {
        ILink projectLink = getProjectLink(projectName);
        return projectLink.getHref().split("=")[1];
    }
}

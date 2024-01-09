package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectPage extends Form {

    private final ITextBox projectNameTxb = getElementFactory().getTextBox(By.id("projectName"), "Project name text box");
    private final IButton saveProjectBtn = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Save project button");
    private final ILabel successAlert = getElementFactory().getLabel(By.xpath("//div[contains(@class, 'alert-success')]"), "Saved project success alert");

    public AddProjectPage() {
        super(By.id("addProjectForm"), "Add project page");
    }

    public void setProjectName(String projectName) {
        projectNameTxb.type(projectName);
    }

    public void clickSaveProjectBtn() {
        saveProjectBtn.click();
    }

    public boolean isSuccessAlertDisplayed() {
        return successAlert.state().isDisplayed();
    }
}

package pages.forms;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FooterForm extends Form {

    private final ILabel versionText = getElementFactory().getLabel(By.xpath("//footer//span"), "Version text");

    public FooterForm() {
        super(By.cssSelector(".footer"), "Footer");
    }

    public String getVersionText() {
        return versionText.getText();
    }
}

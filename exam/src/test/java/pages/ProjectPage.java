package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class ProjectPage extends Form {

    private static final String COLUMN_XPATH = "//table[@id='allTests']//tr//td[%s]";

    public ProjectPage() {
        super(By.id("allTests"), "Project page");
    }

    public List<String> getColumnData(String column) {
        List<ILabel> cells = getElementFactory().findElements(By.xpath(String.format(COLUMN_XPATH, column)), ElementType.LABEL);
        return cells.stream()
                .map(cell -> cell.getText())
                .toList();
    }
}

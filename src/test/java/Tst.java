import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ComboBoxElement;

public class Tst extends TestBenchTestCase {

    @Before
    public void createDriver() {
        setDriver(new ChromeDriver());
    }

    @After
    public void teardown() {
        getDriver().quit();
    }

    @Test
    public void changeComboBoxValue() throws Exception {
        getDriver().get("http://localhost:8080");
        setComboBoxValue("abc123");
        setComboBoxValue("11");
        setComboBoxValue("abc123");
    }

    public void setComboBoxValue(final String value) {
        ComboBoxElement combobox = $(ComboBoxElement.class).first();
        if (combobox.getPopupSuggestions().contains(value)) {
            // Select existing item
            combobox.selectByText(value);
        } else {
            // Enter new item
            combobox.clear();
            combobox.sendKeys(value);
            combobox.sendKeys(Keys.ENTER);
        }
    }
}

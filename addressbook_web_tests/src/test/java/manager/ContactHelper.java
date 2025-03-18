package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("firstname"))) {
            click(By.linkText("add new"));
        }
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("firstname"))) {
            click(By.linkText("home"));
        }
    }

    public void createContact(ContactData contact) {
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact() {
        openContactPage();
        selectContact();
        removeSelectedContact();
        returnToHomePage();
    }

    public void modifyContact(ContactData modifiedContact) {
        openHomePage();
        selectContact();
        initContactModification();
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    private void initContactCreation() {
        click(By.name("firstname"));
    }

    private void removeSelectedContact() {
        click(By.name("delete"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), "first name");
        type(By.name("middlename"), "middle name");
        type(By.name("lastname"), "last name");
        type(By.name("nickname"), "nickname");
        type(By.name("title"), "title");
        type(By.name("company"), "company");
        type(By.name("address"), "address");
        type(By.name("home"), "home");
        type(By.name("mobile"), "mobile");
        type(By.name("work"), "work");
        type(By.name("fax"), "fax");
        type(By.name("email"), "email");
        type(By.name("email2"), "email2");
        type(By.name("email3"), "email3");
    }

    private void initContactModification() {
        click(By.xpath("//img[@alt=\'Edit\']"));
    }

    private void selectContact() {
        click(By.xpath("//table[@id=\'maintable\']/tbody/tr[2]/td/input"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
}

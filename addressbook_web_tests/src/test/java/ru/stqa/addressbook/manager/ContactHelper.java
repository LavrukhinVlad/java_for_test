package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void create(ContactData contact, GroupData group) {
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select((WebElement) manager.driver.findElements(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeContact(ContactData contact) {
        openContactPage();
        selectContact(contact);
        removeSelectedContacts();
        returnToHomePage();
    }

    public void removeContactInGroup(ContactData contact, GroupData group) {
        openContactPage();
        selectGroups(group);
        selectContact(contact);
        removeSelectedContacts();
        returnToHomePage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    public void addContact(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectAddGroup(group);
        submitAddContact();
        returnToHomePage();
    }

    private void selectAddGroup(GroupData group) {
        new Select((WebElement) manager.driver.findElements(By.name("to_group"))).selectByValue(group.id());
    }

    private void selectGroups(GroupData group) {
        new Select((WebElement) manager.driver.findElements(By.name("group"))).selectByValue(group.id());
    }

    private void submitAddContact() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    private void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    private void initContactCreation() {
        click(By.name("firstname"));
    }

    private void removeSelectedContacts() {
        click(By.name("delete"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("nickname"), contact.nickname());
        attach(By.name("photo"), contact.photo());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("fax"), contact.fax());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
    }

    private void initContactModification(ContactData contacts) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contacts.id())));
    }

    private void selectContact(ContactData contacts) {
        click(By.cssSelector(String.format("input[value='%s']", contacts.id())));
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        var result = new ArrayList<ContactData>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var firstname = row.findElements(By.tagName("td")).get(2).getText();
            result.add(new ContactData().withId(id).withFirstname(firstname));
        }
        return result;
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public String getAddress(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public ContactData searchContactForAddInGroup(List<ContactData> oldContacts, List<ContactData> oldRelated) {
        for (ContactData contact : oldContacts) {
            if (!oldRelated.contains(contact)) {
                return contact;
            }
        }
        return  null;
    }
}

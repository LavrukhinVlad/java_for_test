package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContact(){
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData("firstname", "middlename", "lastname", "nickname", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3"));
        }
        app.contact().modifyContact(new ContactData().withFirstname("modified name"));
    }
}

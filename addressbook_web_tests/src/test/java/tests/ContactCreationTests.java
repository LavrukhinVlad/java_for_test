import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactCreationTests extends TestBase {

    @Test
    public void CanCreateContact() {
        int contactCount = app.contact().getCount();
        app.contact().createContact(new ContactData("firstname", "middlename", "lastname", "nickname", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3"));
        int newContactCount = app.contact().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    public void CanCreateContactWithEmptyName() {
        app.contact().createContact(new ContactData());
    }

    @Test
    public void CanCreateContactWithNameOnly() {
        var emptyContact = new ContactData();
        var contactWithName = emptyContact.withFirstname("some name");
        app.contact().createContact(new ContactData().withFirstname("some name"));
    }
}

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData("firstname", "middlename", "lastname", "nickname", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3"));
        }
        app.contact().removeContact();
    }

}

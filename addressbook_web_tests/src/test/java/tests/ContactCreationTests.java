import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "firstname")) {
            for (var middlename : List.of("", "middlename")) {
                for (var lastname : List.of("", "lastname")) {
                    for (var nickname : List.of("", "nickname")) {
                        for (var title : List.of("", "title")) {
                            for (var company : List.of("", "company")) {
                                for (var address : List.of("", "address")) {
                                    for (var home : List.of("", "home")) {
                                        for (var mobile : List.of("", "mobile")) {
                                            for (var work : List.of("", "work")) {
                                                for (var fax : List.of("", "fax")) {
                                                    for (var email : List.of("", "email")) {
                                                        for (var email2 : List.of("", "email2")) {
                                                            for (var email3 : List.of("", "email3")) {
                                                                result.add(new ContactData()
                                                                        .withFirstname(firstname)
                                                                        .withMiddlename(middlename)
                                                                        .withLastname(lastname)
                                                                        .withNickname(nickname)
                                                                        .withTitle(title)
                                                                        .withCompany(company)
                                                                        .withAddress(address)
                                                                        .withHome(home)
                                                                        .withMobile(mobile)
                                                                        .withWork(work)
                                                                        .withFax(fax)
                                                                        .withEmail(email)
                                                                        .withEmail2(email2)
                                                                        .withEmail3(email3));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstname(randomString(i * 10))
                    .withMiddlename(randomString(i * 10))
                    .withLastname(randomString(i * 10))
                    .withNickname(randomString(i * 10))
                    .withTitle(randomString(i * 10))
                    .withCompany(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withHome(randomString(i * 10))
                    .withMobile(randomString(i * 10))
                    .withWork(randomString(i * 10))
                    .withFax(randomString(i * 10))
                    .withEmail(randomString(i * 10))
                    .withEmail2(randomString(i * 10))
                    .withEmail3(randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void CanCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contact().getCount();
        app.contact().createContact(contact);
        int newContactCount = app.contact().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "firstname'", "", "", "", "", "", "", "", "", "", "", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.contact().getCount();
        app.contact().createContact(contact);
        int newContactCount = app.contact().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }

}

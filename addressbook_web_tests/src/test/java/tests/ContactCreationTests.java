import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
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
        var oldContacts = app.contact().getList();
        app.contact().createContact(contact);
        var newContacts = app.contact().getList();
        Comparator<ContactData> compareBuId = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareBuId);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
                .withFirstname("")
                .withMiddlename("")
                .withLastname("")
                .withNickname("")
                .withTitle("")
                .withCompany("")
                .withAddress("")
                .withHome("")
                .withMobile("")
                .withWork("")
                .withFax("")
                .withEmail("")
                .withEmail2("")
                .withEmail3(""));
        expectedList.sort(compareBuId);
        Assertions.assertEquals(newContacts, expectedList);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "firstname'", "", "", "", "", "", "", "", "", "", "", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.contact().getList();
        app.contact().createContact(contact);
        var newContacts = app.contact().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }
}

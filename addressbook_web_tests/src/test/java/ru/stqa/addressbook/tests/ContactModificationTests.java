package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstname(CommonFunctions.randomString(10));
        app.contact().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }
}

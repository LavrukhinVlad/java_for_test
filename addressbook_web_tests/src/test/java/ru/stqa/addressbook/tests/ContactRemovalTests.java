package ru.stqa.addressbook.tests;

import org.junit.platform.commons.util.CollectionUtils;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contact().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveContactInGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "", ""));
        }
        var contact = app.hbm().getContactList().get(0);
        if (app.hbm().getContactsInGroup(group) != null && app.hbm().getContactsInGroup(group).size() == 0) {
            app.contact().addContact(contact, group);
        }
        var oldRelated = app.hbm().getContactsInGroup(group);
        var rnd = new Random();
        var index = rnd.nextInt(oldRelated.size());
        app.contact().removeContactInGroup(oldRelated.get(index), group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(contact);
        Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newRelated));
    }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "", ""));
        }
        app.contact().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }
}

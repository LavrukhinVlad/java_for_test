package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static ru.stqa.addressbook.common.CommonFunctions.randomFile;

public class ContactCreationTests extends TestBase {

    public static Stream<ContactData> randomContacts() {
        Supplier<ContactData> randomContact = () -> new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(20))
                .withAddress(CommonFunctions.randomString(30));
        return Stream.generate(randomContact).limit(1);
    }

    @ParameterizedTest
    @MethodSource("randomContacts")
    public void CanCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contact().createContact(contact);
        var newContacts = app.hbm().getContactList();

        var extraContacts = newContacts.stream().filter(c -> !oldContacts.contains(c)).toList();
        var newId = extraContacts.get(0).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newId));
        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<>(List.of(
                new ContactData("", "firstname'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")));
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

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contact().create(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var maxId = newRelated.get(newRelated.size() - 1).id();
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.withId(maxId));
        Assertions.assertEquals(newRelated, expectedList);
    }
}

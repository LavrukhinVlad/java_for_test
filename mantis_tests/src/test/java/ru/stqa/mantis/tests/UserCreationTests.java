package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase{

    DeveloperMailUser user;

    @Test
    void canCreateuser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        //        app.user().startCreation(user);
        //
        //        var message = app.mail().receive(email, password, Duration.ofSeconds(10));
        //        var url = CommonFunctions.extractUrl(message.get(0).content());
        //
        //        app.user().finishCreation(url, user, password);
        //
        //        app.http().login(user, password);
        //        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}

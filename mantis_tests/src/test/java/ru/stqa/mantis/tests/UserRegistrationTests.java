package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;


public class UserRegistrationTests extends TestBase {


@Test
    void canRegisterUser() {
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
        var email = String.format("%s@localhost", CommonFunctions.randomString(8));

        // заполняем форму создания и отправляем (браузер)
        var password = "password";
        var username = CommonFunctions.randomString(5);
        app.jamesCli().addUser(email, password);
        app.http().signUp();
        app.user().signUp(username, email);

        // ждем почту (MailHelper)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(15));
        System.out.println("messages " + messages);

        // извлекаем ссылку из письма
        var url = app.mail().extractUrl(messages);
        System.out.println("url " + url);

        // проходим по ссылке и завершаем регистрацию (браузер)
        app.user().finishCreation((String) url, username, password);
        app.http().login(username, password);

        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canRegistrationUserWithRestApi() {
        var email = String.format("%s@localhost", CommonFunctions.randomString(8));
        var password = "password";
        var username = CommonFunctions.randomString(5);
        app.jamesApi().addUser(email, password);
        app.rest().createUser(new UserData()
                .withUsername(username)
                .withEmail(email));
        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        var url = app.mail().extractUrl(messages);
        app.user().editAccount((String) url, username, password);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}

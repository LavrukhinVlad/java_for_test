package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
        // заполняем форму создания и отправляем (браузер) (создать класс помощник один для браузера)
        // получаем(т.е. ждём) почту (MailHelper)
        // извлекаем ссылку из письма     (void canExtractUrl())
        // проходим по ссылке и завершаем регистрацию (браузер) (создать класс помощник один для браузера)
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
    }

}

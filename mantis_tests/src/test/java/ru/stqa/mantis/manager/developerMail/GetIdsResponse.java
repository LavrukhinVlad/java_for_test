package ru.stqa.mantis.manager.developerMail;

import ru.stqa.mantis.model.DeveloperMailUser;

import java.util.List;

public record GetIdsResponse(Boolean success, Object errors, List<String> result) {
}
package com.example.project.utils;

import com.example.project.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidationUtils {
    private final Validator validator;

    public <T> void validationRequest(T req) {

        if (req != null) {
            Set<ConstraintViolation<T>> result = validator.validate(req);
            if (!result.isEmpty()) {
                String resultValidations = result.stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((s1, s2) -> s1 + ". " + s2).orElse("");
                log.error("Переданный в запросе json не валиден, ошибки валидации: {}", resultValidations);
                throw ValidationException.builder().techMessage(resultValidations).build();
            }
        }
    }
}

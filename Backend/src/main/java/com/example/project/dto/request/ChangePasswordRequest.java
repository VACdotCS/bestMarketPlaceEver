package com.example.project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {
    @NotBlank(message = "Пароль неверный")
    private String currentPassword;
    @NotBlank(message = "Неправильный пароль")
    @Pattern(regexp = "^$|^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~])[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~]{8,100}$", message = "Неправильный пароль")
    private String newPassword;
    private String confirmationPassword;
}
package com.example.project.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Неправильное имя")
    @Pattern(regexp = "^$|^([А-Яа-я\\s]{1,75}|[A-Za-z\\s]{1,75})$", message = "Неправильное имя")
     private String username;
    @NotBlank(message = "Неправильный пароль")
    @Pattern(regexp = "^$|^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~])[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~]{8,100}$", message = "Неправильный пароль")
    private String password;
    @NotBlank(message = "Неправильный телефон")
    @Pattern(regexp = "^$|^(\\+)?((\\d{2,3}) ?\\d|\\d)(([ -]?\\d)|( ?(\\d{2,3}) ?)){5,12}\\d$", message = "Неправильный телефон")
     private String phone;
    @NotBlank(message = "Неправильный e-mail")
    @Pattern(regexp = "^$|^[\\w_.]+@([\\w-]+\\.)+[a-zA-Z]{2,4}$", message = "Неправильный e-mail")
   private String email;
}

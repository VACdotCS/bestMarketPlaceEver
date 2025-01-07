package com.example.project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeCardUserRequest {
    @NotBlank(message = "Заполните номер карты")
    @Pattern(regexp = "^$|\\d{16}", message = "Номер карты должен содержать 16 цифр")
    private String card_number;

    @NotBlank(message = "Заполните CVC")
    @Pattern(regexp = "^$|\\d{3,4}", message = "CVC должен содержать 3 или 4 цифры")
    private String CVC;

    @NotBlank(message = "Заполните дату")
    @Pattern(regexp = "^\\d{2}/\\d{4}$", message = "Invalid card validity format")
    private String validity;
}

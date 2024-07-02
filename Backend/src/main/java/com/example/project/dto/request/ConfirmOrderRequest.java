package com.example.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ConfirmOrderRequest {
    List<Integer> received;

    List<Integer> returned;
}

package com.example.project.dto.response;

public record UpdateOrderStatusRequest(
        Integer orderId,
        Boolean approved
) {}

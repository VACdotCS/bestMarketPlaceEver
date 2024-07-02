package com.example.project.dto.response;

import com.example.project.dto.serializer.PickupPointDTOSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonSerialize(using = PickupPointDTOSerializer.class)
@Data
@AllArgsConstructor
public class PickupPointDTO {
    private int id;
    private String address;
    private Integer managerId;
    private String managerName;
    private Double income;
}
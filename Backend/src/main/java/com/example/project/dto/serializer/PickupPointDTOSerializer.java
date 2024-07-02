package com.example.project.dto.serializer;

import com.example.project.dto.response.PickupPointDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.project.entity.Role.ADMIN;
import static com.example.project.entity.Role.MANAGER;

@Component
public class PickupPointDTOSerializer extends StdSerializer<PickupPointDTO> {

    public PickupPointDTOSerializer() {
        this(null);
    }

    public PickupPointDTOSerializer(Class<PickupPointDTO> t) {
        super(t);
    }

    @Override
    public void serialize(PickupPointDTO pickupPoint, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();

        jgen.writeStartObject();
        jgen.writeStringField("id", String.valueOf(pickupPoint.getId()));
        jgen.writeStringField("address", pickupPoint.getAddress());
        jgen.writeStringField("manager_id", String.valueOf(pickupPoint.getManagerId()));
        jgen.writeStringField("manager_name", String.valueOf(pickupPoint.getManagerName()));
        if (role.contains(MANAGER.name()) || role.contains(ADMIN.name())) {
            jgen.writeStringField("income", String.valueOf(pickupPoint.getIncome()));
        }
        jgen.writeEndObject();
    }
}
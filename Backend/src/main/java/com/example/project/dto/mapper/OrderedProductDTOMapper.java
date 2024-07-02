package com.example.project.dto.mapper;

import com.example.project.dto.response.OrderedProductDTO;
import com.example.project.dto.response.CompactProductDTO;
import com.example.project.entity.OrderedProduct;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderedProductDTOMapper implements Function<OrderedProduct, OrderedProductDTO> {
    @Override
    public OrderedProductDTO apply(OrderedProduct orderedProduct) {
        CompactProductDTO compactProductDTO = new CompactProductDTO(orderedProduct.getProduct());

        return new OrderedProductDTO(
                compactProductDTO,
                orderedProduct.getCount(),
                orderedProduct.getDiscountPrice(),
                orderedProduct.getDeliveryDays(),
                orderedProduct.getDeliveryStatus().getTitle(),
                orderedProduct.getCompletionDate()
        );
    }

}

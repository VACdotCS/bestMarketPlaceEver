package com.example.project.dto.mapper;

import com.example.project.dto.response.OrderDTO;
import com.example.project.dto.response.OrderedProductDTO;
import com.example.project.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderDTOMapper implements Function<Order, OrderDTO> {
    private OrderedProductDTOMapper orderedProductDTOMapper;

    @Override
    public OrderDTO apply(Order order) {
        List<OrderedProductDTO> products = order.getProducts()
                .stream()
                .map(orderedProductDTOMapper)
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getId(),
                products,
                order.getFormation_date(),
                order.isCompleted()
        );
    }

}
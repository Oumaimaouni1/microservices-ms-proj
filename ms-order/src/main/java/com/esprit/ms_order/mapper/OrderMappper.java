package com.esprit.ms_order.mapper;

import com.esprit.ms_order.dto.OrderDTO;
import com.esprit.ms_order.model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMappper {
    OrderMappper INSTANCE = Mappers.getMapper(OrderMappper.class);
    @Mapping(source = "id", target = "orderId")
    OrderDTO toDto(Orders order);
    @Mapping(source = "orderId", target = "id")
    Orders toEntity(OrderDTO orderDTO);

}

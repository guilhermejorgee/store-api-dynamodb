package com.gui.ecommerceDynamoDB.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gui.ecommerceDynamoDB.model.OrderUser;
import com.gui.ecommerceDynamoDB.model.ProductItemInOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderUserDto {
	
	private String orderId;
	private String orderUserId;
	private List<ProductItemInOrder> items;
	
	public OrderUserDto(OrderUser order) {
		this.orderId = order.getOrderId();
		this.orderUserId = order.getOrderUserId();
		this.items = order.getItems();
	}

}

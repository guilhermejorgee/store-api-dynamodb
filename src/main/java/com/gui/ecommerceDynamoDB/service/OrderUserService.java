package com.gui.ecommerceDynamoDB.service;

import java.util.List;

import com.gui.ecommerceDynamoDB.dto.OrderUserDto;
import com.gui.ecommerceDynamoDB.model.OrderUser;

public interface OrderUserService {
	
	OrderUserDto setOrder(OrderUser order);
	OrderUserDto getOrder(String id);
	List<OrderUserDto> getOrdersUser(String idUser);
	
}

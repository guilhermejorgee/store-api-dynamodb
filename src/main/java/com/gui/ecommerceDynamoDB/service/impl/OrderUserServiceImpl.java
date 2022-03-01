package com.gui.ecommerceDynamoDB.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gui.ecommerceDynamoDB.dto.OrderUserDto;
import com.gui.ecommerceDynamoDB.exception.OrderUserNotFoundException;
import com.gui.ecommerceDynamoDB.exception.UsernameNotFoundException;
import com.gui.ecommerceDynamoDB.model.OrderUser;
import com.gui.ecommerceDynamoDB.repository.OrderUserRepository;
import com.gui.ecommerceDynamoDB.service.OrderUserService;

@Service
public class OrderUserServiceImpl implements OrderUserService {
	

	OrderUserRepository orderRepository;
	AppUserServiceImpl userService;
	
	@Autowired
	public OrderUserServiceImpl(OrderUserRepository orderRepository, AppUserServiceImpl userService) {
		this.orderRepository = orderRepository;
		this.userService = userService;
	}

	@Override
	public OrderUserDto setOrder(OrderUser order) {
		userService.findUser(order.getOrderUserId()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		order.modifyKeysDbPattern();
		return new OrderUserDto(orderRepository.saveOrderDb(order));
	}
	
	@Override
	public OrderUserDto getOrder(String id) {
		return new OrderUserDto(orderRepository.getOrderDb(id).orElseThrow(() -> new OrderUserNotFoundException("Order User Not Found")));
	}
	
	@Override
	public List<OrderUserDto> getOrdersUser(String idUser){
		return orderRepository.getOrdersUserDb(idUser).stream().map(OrderUserDto::new).toList();
	}
	
	

}

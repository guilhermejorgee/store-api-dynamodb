package com.gui.ecommerceDynamoDB.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gui.ecommerceDynamoDB.dto.OrderUserDto;
import com.gui.ecommerceDynamoDB.form.OrderUserForm;
import com.gui.ecommerceDynamoDB.service.impl.OrderUserServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderUserController {
	
	@Autowired
	OrderUserServiceImpl orderService;
	
	@PostMapping
	public ResponseEntity<OrderUserDto> setOrder(@RequestBody @Valid OrderUserForm form){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand("id").toUriString());
		return ResponseEntity.created(uri).body(orderService.setOrder(form.transform()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderUserDto> getOrder(@PathVariable String id){
		return ResponseEntity.ok(orderService.getOrder(id));
	}
	
	@GetMapping("/user/{idUser}")
	public ResponseEntity<List<OrderUserDto>> getOrdersUser(@PathVariable String idUser){
		return ResponseEntity.ok(orderService.getOrdersUser(idUser));
	}
	
/*	public ResponseEntity<String> updateOrder(){
		
	}*/
	
}

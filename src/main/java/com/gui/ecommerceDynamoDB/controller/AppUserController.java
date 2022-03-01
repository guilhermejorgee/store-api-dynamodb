package com.gui.ecommerceDynamoDB.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gui.ecommerceDynamoDB.dto.AppUserDto;
import com.gui.ecommerceDynamoDB.dto.AppUserWithAddressDto;
import com.gui.ecommerceDynamoDB.form.AppUserForm;
import com.gui.ecommerceDynamoDB.form.AppUserFormUpdate;
import com.gui.ecommerceDynamoDB.model.AddressUser;
import com.gui.ecommerceDynamoDB.service.AppUserService;

@RestController
@RequestMapping("/user")
public class AppUserController {
	
	@Autowired
	AppUserService userService;
	
	@GetMapping
	public ResponseEntity<List<? extends AppUserDto>> getUsers(@RequestParam boolean address){
		return ResponseEntity.ok(userService.getUsers(address));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppUserDto> getUser(@PathVariable String id, @RequestParam boolean address){
		return ResponseEntity.ok(userService.getUser(id, address));
	}
	
	@PostMapping
	public ResponseEntity<AppUserDto> setUser(@RequestBody @Valid AppUserForm form){
		AppUserDto user = userService.setUser(form.transform());
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/{id}?address=true/false").buildAndExpand(user.getUserId()).toUriString());
		return ResponseEntity.created(uri).body(user);
	}
	
	@PutMapping("/address/{idUser}")
	public ResponseEntity<AppUserWithAddressDto> setAddressUser(@RequestBody @Valid AddressUser address, @PathVariable String idUser){
		return ResponseEntity.ok(userService.setAddressUser(address, idUser));
	}
	
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody AppUserFormUpdate form){
		return ResponseEntity.ok(userService.updateUser(form));
	}
	
	

}

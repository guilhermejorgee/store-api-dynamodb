package com.gui.ecommerceDynamoDB.service;

import java.util.List;

import com.gui.ecommerceDynamoDB.dto.AppUserDto;
import com.gui.ecommerceDynamoDB.dto.AppUserWithAddressDto;
import com.gui.ecommerceDynamoDB.form.AppUserFormUpdate;
import com.gui.ecommerceDynamoDB.model.AddressUser;
import com.gui.ecommerceDynamoDB.model.AppUser;

public interface AppUserService {
	
	AppUserDto setUser(AppUser user);
	AppUserDto getUser(String idUser, boolean address);
	AppUserWithAddressDto setAddressUser(AddressUser address, String idUser);
	List<? extends AppUserDto> getUsers(boolean address);
	String updateUser(AppUserFormUpdate form);
	
}

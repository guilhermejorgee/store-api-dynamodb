package com.gui.ecommerceDynamoDB.dto;

import java.util.List;

import com.gui.ecommerceDynamoDB.model.AddressUser;
import com.gui.ecommerceDynamoDB.model.AppUser;

import lombok.Getter;

@Getter
public class AppUserWithAddressDto extends AppUserDto {
	
	List<AddressUser> address;
	
	public AppUserWithAddressDto(AppUser user) {
		super(user);
		this.address = user.getAddress();
	}
}

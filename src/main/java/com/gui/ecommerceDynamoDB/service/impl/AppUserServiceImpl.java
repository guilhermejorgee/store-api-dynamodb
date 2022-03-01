package com.gui.ecommerceDynamoDB.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gui.ecommerceDynamoDB.dto.AppUserDto;
import com.gui.ecommerceDynamoDB.dto.AppUserWithAddressDto;
import com.gui.ecommerceDynamoDB.exception.UsernameNotFoundException;
import com.gui.ecommerceDynamoDB.form.AppUserFormUpdate;
import com.gui.ecommerceDynamoDB.model.AddressUser;
import com.gui.ecommerceDynamoDB.model.AppUser;
import com.gui.ecommerceDynamoDB.repository.AppUserRepository;
import com.gui.ecommerceDynamoDB.service.AppUserService;
import com.gui.ecommerceDynamoDB.util.IdentificationTagsDB;

@Service
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	AppUserRepository userRepository;
	
	@Override
	public AppUserDto setUser(AppUser user) {
		user.modifyKeysDbPattern();
		userRepository.saveUserDb(user);
		return new AppUserDto(user);
	}
	
	@Override
	public AppUserDto getUser(String idUser, boolean address) {		
		AppUser user = findUser(idUser).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));			
		if(address) {
			return new AppUserWithAddressDto(user);
		}
		return new AppUserDto(user);
	}
	
	@Override
	public List<? extends AppUserDto> getUsers(boolean address){
		if(address) {
			return findUsers().stream().map(e -> new AppUserWithAddressDto(e)).toList();
		}
		return findUsers().stream().map(e -> new AppUserDto(e)).toList();
	}
	
	@Override
	public AppUserWithAddressDto setAddressUser(AddressUser address, String idUser) {	
		AppUser user = findUser(idUser).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		user.addAddress(address);
		userRepository.saveUserDb(user);
		return new AppUserWithAddressDto(user);
	}
	
	@Override
	public String updateUser(AppUserFormUpdate form) {	
		AppUser user = findUser(form.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		user.update(form);
		userRepository.saveUserDb(user);
		return "Atualizado com sucesso";
	}
	
	
	Optional<AppUser> findUser(String idUser) {
		var keys = IdentificationTagsDB.setTagsUserProfile(idUser);
		return userRepository.getUserDb(
				keys.get(IdentificationTagsDB.ID_USER_TABLE),
				keys.get(IdentificationTagsDB.ENTITY_USER_TABLE));
	}
	
	private List<AppUser> findUsers(){
		return userRepository.getUsersDb();
	}

	

}

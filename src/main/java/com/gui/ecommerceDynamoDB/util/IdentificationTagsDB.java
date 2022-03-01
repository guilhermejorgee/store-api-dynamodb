package com.gui.ecommerceDynamoDB.util;

import java.util.HashMap;
import java.util.Map;
public class IdentificationTagsDB {
	
	public static final String ENTITY_USER_TABLE = "PROFILE#";
	public static final String ENTITY_ORDER_TABLE = "ORDER#";
	
	public static final String ID_USER_TABLE = "USER#";
	
	public static final String ID_USER_FIELD = "user_id";
	
	public static final String ID_ORDER_FIELD = "order_id";
	
	public static final String GSI_INVERSE = "GSI-INVERSEPK";
	
	public static Map<String, String> setTagsUserProfile(String idUser) {
		Map<String, String> keys = new HashMap<>();
		keys.put(ID_USER_TABLE, ID_USER_TABLE + idUser);
		keys.put(ENTITY_USER_TABLE, ENTITY_USER_TABLE);
		return keys;
	}
	
	public static Map<String, String> setTagUserOrder(String idUser, String idOrder){
		Map<String, String> keys = new HashMap<>();
		keys.put(ID_USER_TABLE, ID_USER_TABLE + idUser);
		keys.put(ENTITY_ORDER_TABLE, ENTITY_ORDER_TABLE + idOrder);
		return keys;
	}

}

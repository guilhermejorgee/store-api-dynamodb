package com.gui.ecommerceDynamoDB.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gui.ecommerceDynamoDB.model.OrderUser;
import com.gui.ecommerceDynamoDB.model.ProductItemInOrder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderUserForm {	
	
	@NotNull @NotEmpty
	private String orderUserId;
	
	@NotNull
	private List<ProductItemInOrder> items;

	public OrderUser transform() {
		return new OrderUser(this.orderUserId, this.items);
	}
	
}

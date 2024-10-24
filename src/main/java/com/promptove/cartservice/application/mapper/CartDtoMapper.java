package com.promptove.cartservice.application.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.out.CartTransactionDto;
import com.promptove.cartservice.domain.model.Cart;

@Component
public class CartDtoMapper {

	public CartTransactionDto toDto(Cart cart) {
		return CartTransactionDto.builder()
			.id(cart.getId())
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdAt(cart.getCreatedAt())
			.build();
	}
}

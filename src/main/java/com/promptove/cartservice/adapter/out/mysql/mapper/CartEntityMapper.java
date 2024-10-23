package com.promptove.cartservice.adapter.out.mysql.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.application.port.dto.CartDto;
import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;
import com.promptove.cartservice.domain.model.Cart;

@Component
public class CartEntityMapper {

	public CartEntity toEntity(CartDto cartDto) {
		return CartEntity.builder()
			.memberUuid(cartDto.getMemberUuid())
			.productUuid(cartDto.getProductUuid())
			.selected(cartDto.isSelected())
			.deleted(cartDto.isDeleted())
			.createdAt(cartDto.getCreatedAt())
			.build();
	}

	public Cart toDomain(CartEntity cartEntity) {
		return Cart.builder()
			.memberUuid(cartEntity.getMemberUuid())
			.productUuid(cartEntity.getProductUuid())
			.selected(cartEntity.isSelected())
			.deleted(cartEntity.isDeleted())
			.createdAt(cartEntity.getCreatedAt())
			.build();
	}
}

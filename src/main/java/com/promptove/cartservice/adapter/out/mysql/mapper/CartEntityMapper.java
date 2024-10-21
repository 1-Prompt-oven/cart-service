package com.promptove.cartservice.adapter.out.mysql.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;
import com.promptove.cartservice.domain.Cart;

@Component
public class CartEntityMapper {

	public CartEntity toEntity(Cart cart) {
		return CartEntity.builder()
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdAt(cart.getCreatedAt())
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

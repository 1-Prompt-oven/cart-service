package com.promptove.cartservice.adapter.out.mysql.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;
import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.out.CartTransactionDto;
import com.promptove.cartservice.domain.model.Cart;

@Component
public class CartEntityMapper {

	public CartEntity toEntity(CartTransactionDto cartTransactionDto) {
		return CartEntity.builder()
			.memberUuid(cartTransactionDto.getMemberUuid())
			.productUuid(cartTransactionDto.getProductUuid())
			.selected(cartTransactionDto.isSelected())
			.deleted(cartTransactionDto.isDeleted())
			.createdAt(cartTransactionDto.getCreatedAt())
			.build();
	}

	public Cart EntityToDomain(CartEntity cartEntity) {
		return Cart.builder()
			.memberUuid(cartEntity.getMemberUuid())
			.productUuid(cartEntity.getProductUuid())
			.selected(cartEntity.isSelected())
			.deleted(cartEntity.isDeleted())
			.createdAt(cartEntity.getCreatedAt())
			.build();
	}

	public CartRequestDto EntityToDto(CartEntity cartEntity) {
		return CartRequestDto.builder()
			.memberUuid(cartEntity.getMemberUuid())
			.productUuid(cartEntity.getProductUuid())
			.selected(cartEntity.isSelected())
			.deleted(cartEntity.isDeleted())
			.createdAt(cartEntity.getCreatedAt())
			.build();
	}
}

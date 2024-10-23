package com.promptove.cartservice.application.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.out.CartTransactionDto;
import com.promptove.cartservice.domain.model.Cart;

@Component
public class CartDtoMapper {

	// DTO를 도메인으로
	public Cart DtoToDomain(CartRequestDto cartRequestDto) {
		return Cart.builder()
			.memberUuid(cartRequestDto.getMemberUuid())
			.productUuid(cartRequestDto.getProductUuid())
			.selected(cartRequestDto.isSelected())
			.deleted(cartRequestDto.isDeleted())
			.createdAt(cartRequestDto.getCreatedAt())
			.build();
	}

	// 도메인을 DTO로
	public CartRequestDto DomainToDto(Cart cart) {
		return CartRequestDto.builder()
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdAt(cart.getCreatedAt())
			.build();
	}

	public CartTransactionDto toDto(Cart cart) {
		return CartTransactionDto.builder()
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdAt(cart.getCreatedAt())
			.build();
	}
}

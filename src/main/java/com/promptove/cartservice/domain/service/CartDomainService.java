package com.promptove.cartservice.domain.service;

import org.springframework.stereotype.Service;

import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.out.CartTransactionDto;
import com.promptove.cartservice.domain.model.Cart;

@Service
public class CartDomainService {

	public Cart createCart(CartRequestDto cartRequestDto) {
		return Cart.builder()
			.memberUuid(cartRequestDto.getMemberUuid())
			.productUuid(cartRequestDto.getProductUuid())
			.selected(cartRequestDto.isSelected())
			.deleted(false)
			.build();
	}

	public Cart updateCart(CartTransactionDto cartTransactionDto, CartRequestDto cartRequestDto) {
		return Cart.builder()
			.id(cartTransactionDto.getId())
			.memberUuid(cartTransactionDto.getMemberUuid())
			.productUuid(cartTransactionDto.getProductUuid())
			.selected(cartRequestDto.isSelected())
			.deleted(cartTransactionDto.isDeleted())
			.build();
	}

	public Cart deleteCart(CartTransactionDto cartTransactionDto, CartRequestDto cartRequestDto) {
		return Cart.builder()
			.id(cartTransactionDto.getId())
			.memberUuid(cartTransactionDto.getMemberUuid())
			.productUuid(cartTransactionDto.getProductUuid())
			.selected(cartTransactionDto.isSelected())
			.deleted(true)
			.build();
	}
}

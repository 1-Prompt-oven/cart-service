package com.promptoven.cartservice.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promptoven.cartservice.application.port.in.CartRequestDto;
import com.promptoven.cartservice.application.port.out.CartOutportDto;
import com.promptoven.cartservice.domain.model.Cart;

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

	public List<Cart> getCart(List<CartOutportDto> cartOutportDtoList) {
		return cartOutportDtoList.stream().map(cartTransactionDto -> Cart.builder()
			.id(cartTransactionDto.getId())
			.memberUuid(cartTransactionDto.getMemberUuid())
			.productUuid(cartTransactionDto.getProductUuid())
			.selected(cartTransactionDto.isSelected())
			.deleted(cartTransactionDto.isDeleted())
			.createdDate(cartTransactionDto.getCreatedDate())
			.lastModifiedDate(cartTransactionDto.getLastModifiedDate())
			.build()).toList();
	}

	public Cart updateCart(CartOutportDto cartOutportDto, CartRequestDto cartRequestDto) {
		return Cart.builder()
			.id(cartOutportDto.getId())
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(cartRequestDto.isSelected())
			.deleted(cartOutportDto.isDeleted())
			.build();
	}

	public Cart updateExistCart(CartOutportDto cartOutportDto) {
		return Cart.builder()
			.id(cartOutportDto.getId())
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(true)
			.deleted(false)
			.build();
	}

	public Cart deleteCart(CartOutportDto cartOutportDto) {
		return Cart.builder()
			.id(cartOutportDto.getId())
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(cartOutportDto.isSelected())
			.deleted(true)
			.build();
	}
}

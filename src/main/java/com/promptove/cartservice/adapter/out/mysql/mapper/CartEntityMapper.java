package com.promptove.cartservice.adapter.out.mysql.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;
import com.promptove.cartservice.application.port.out.CartTransactionDto;
import com.promptove.cartservice.domain.model.Cart;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	public CartEntity toUpdateEntity(CartTransactionDto cartTransactionDto) {

		log.info("cartTransactionDto: {}", cartTransactionDto.toString());

		return CartEntity.builder()
			.id(cartTransactionDto.getId())
			.memberUuid(cartTransactionDto.getMemberUuid())
			.productUuid(cartTransactionDto.getProductUuid())
			.selected(cartTransactionDto.isSelected())
			.deleted(cartTransactionDto.isDeleted())
			.createdAt(cartTransactionDto.getCreatedAt())
			.build();
	}

	public CartEntity toDeleteEntity(CartTransactionDto cartTransactionDto) {

		return CartEntity.builder()
			.id(cartTransactionDto.getId())
			.memberUuid(cartTransactionDto.getMemberUuid())
			.productUuid(cartTransactionDto.getProductUuid())
			.selected(cartTransactionDto.isSelected())
			.deleted(cartTransactionDto.isDeleted())
			.createdAt(cartTransactionDto.getCreatedAt())
			.build();
	}

	public CartTransactionDto toDto(CartEntity cartEntity) {
		return CartTransactionDto.builder()
			.id(cartEntity.getId())
			.memberUuid(cartEntity.getMemberUuid())
			.productUuid(cartEntity.getProductUuid())
			.selected(cartEntity.isSelected())
			.deleted(cartEntity.isDeleted())
			.createdAt(cartEntity.getCreatedAt())
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
}

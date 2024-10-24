package com.promptove.cartservice.application.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promptove.cartservice.application.mapper.CartDtoMapper;
import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.in.CartUseCase;
import com.promptove.cartservice.application.port.out.CartRepositoryPort;
import com.promptove.cartservice.application.port.out.CartTransactionDto;
import com.promptove.cartservice.domain.model.Cart;
import com.promptove.cartservice.domain.service.CartDomainService;

@Service
public class CartService implements CartUseCase {

	private final CartRepositoryPort cartRepositoryPort;
	private final CartDomainService cartDomainService;
	private final CartDtoMapper cartDtoMapper;

	public CartService(@Qualifier("cartMysqlAdapter") CartRepositoryPort cartRepositoryPort) {
		this.cartRepositoryPort = cartRepositoryPort;
		this.cartDomainService = new CartDomainService();
		this.cartDtoMapper = new CartDtoMapper();
	}

	@Override
	@Transactional
	public void createCart(CartRequestDto cartCreateRequestDto) {

		cartRepositoryPort.save(cartDtoMapper.toDto(cartDomainService.createCart(cartCreateRequestDto)));

		// // 예전에 담은 적이 없으면
		// if (cart == null) {
		// 	cartRepositoryPort.save(
		// 		Cart.from(cartDto.getMemberUuid(), cartDto.getProductUuid(), cartDto.isSelected(),
		// 			cartDto.isDeleted(), cartDto.getCreatedAt()));
		// } else {
		// 	// 예전에 담은 적이 있고 삭제 된 상태면
		// 	if (cart.isDeleted()) {
		// 		cart.updateDeleted(false);
		// 		cartRepositoryPort.save(cart);
		// 	}
		// }
	}

	// @Override
	// public List<Cart> getCart(CartRequestDto cartRequestDto) {
	// 	Cart cart = cartDomainService.createCart(cartRequestDto);
	// 	return cartRepositoryPort.getCart(cartDtoMapper.toDto(cart));
	// }

	@Transactional
	@Override
	public void updateCartItem(CartRequestDto cartUpdateRequestDto) {

		CartTransactionDto cartTransactionDto = cartRepositoryPort.getCartByProductUuidAndMemberUuid(
			cartUpdateRequestDto.getMemberUuid(), cartUpdateRequestDto.getProductUuid()).orElse(null);

		// 장바구니에 없거나 Soft Delete 된 경우
		if (cartTransactionDto == null || cartTransactionDto.isDeleted()) {
			throw new IllegalArgumentException("해당 제품이 장바구니에 없습니다.");
		} else {
			Cart cart = cartDomainService.updateCart(cartTransactionDto, cartUpdateRequestDto);
			cartRepositoryPort.updateCartItem(cartDtoMapper.toDto(cart));
		}
	}

	@Transactional
	@Override
	public void deleteCartItem(CartRequestDto cartDeleteRequestDto) {

		CartTransactionDto cartTransactionDto = cartRepositoryPort.getCartByProductUuidAndMemberUuid(
			cartDeleteRequestDto.getMemberUuid(), cartDeleteRequestDto.getProductUuid()).orElse(null);

		if (cartTransactionDto == null) {
			throw new IllegalArgumentException("해당 제품이 장바구니에 없습니다.");
		} else {
			Cart cart = cartDomainService.deleteCart(cartTransactionDto, cartDeleteRequestDto);
			cartRepositoryPort.deleteCartItem(cartDtoMapper.toDto(cart));
		}
	}
}


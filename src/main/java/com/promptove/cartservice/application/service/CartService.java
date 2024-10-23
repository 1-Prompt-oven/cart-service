package com.promptove.cartservice.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promptove.cartservice.application.mapper.CartDtoMapper;
import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.in.CartUseCase;
import com.promptove.cartservice.application.port.out.CartRepositoryPort;
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
	public void createCart(CartRequestDto cartRequestDto) {

		cartRepositoryPort.save(cartDtoMapper.toDto(cartDomainService.createCart(cartRequestDto)));

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

	@Override
	public List<Cart> getCart(String memberUuid) {
		return cartRepositoryPort.getCart(memberUuid);
	}

	@Transactional
	@Override
	public void updateCartItem(String memberUuid, String productUuid, boolean isSelected) {
		Cart cart = cartRepositoryPort.getCartByProductUuidAndMemberUuid(productUuid, memberUuid).orElse(null);
		if (cart == null) {
			throw new IllegalArgumentException("해당 제품이 장바구니에 없습니다.");
		} else {
			cart.updateSelected(isSelected);
			cartRepositoryPort.updateCartItem(cart);
		}
	}

	@Transactional
	@Override
	public void deleteCartItem(String memberUuid, String productUuid) {
		cartRepositoryPort.deleteCartItem(memberUuid, productUuid);
	}

	// 제품 삭제 시 장바구니에서 해당 제품 제거 (소프트 삭제)
	@Override
	@Transactional
	public void deleteCartItemByProductUuid(String productUuid) {
		cartRepositoryPort.deleteCartItemsByProductUuid(productUuid);
	}

	// 결제 성공 후 장바구니 비우기
	@Override
	@Transactional
	public void clearCartAfterOrder(String memberUuid) {
		cartRepositoryPort.clearCartByMemberUuid(memberUuid);
	}
}


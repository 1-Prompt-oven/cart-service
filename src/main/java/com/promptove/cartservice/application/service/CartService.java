package com.promptove.cartservice.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promptove.cartservice.adapter.in.web.dto.CartRequestDto;
import com.promptove.cartservice.adapter.in.web.dto.CartUpdateDto;
import com.promptove.cartservice.application.port.in.CartUseCase;
import com.promptove.cartservice.application.port.out.CartRepositoryPort;
import com.promptove.cartservice.domain.Cart;

@Service
public class CartService implements CartUseCase {

	private final CartRepositoryPort cartRepositoryPort;

	public CartService(@Qualifier("cartMysqlAdapter") CartRepositoryPort cartRepositoryPort) {
		this.cartRepositoryPort = cartRepositoryPort;
	}

	@Override
	@Transactional
	public void createCart(CartRequestDto cartRequestDto) {

		Cart cart = cartRepositoryPort.getCartByProductUuidAndMemberUuid(cartRequestDto.getProductUuid(),
			cartRequestDto.getMemberUuid());

		// 예전에 담은 적이 없으면
		if (cart == null) {
			cartRepositoryPort.save(
				Cart.from(cartRequestDto.getMemberUuid(), cartRequestDto.getProductUuid(), cartRequestDto.isSelected(),
					cartRequestDto.isDeleted(), cartRequestDto.getCreatedAt()));
		} else {
			// 예전에 담은 적이 있고 삭제 된 상태면
			if (cart.isDeleted()) {
				cart.updateDeleted(false);
				cartRepositoryPort.save(cart);
			}
		}
	}

	@Override
	public List<Cart> getCart(String memberUuid) {
		return cartRepositoryPort.getCart(memberUuid);
	}

	@Transactional
	@Override
	public void updateCartItem(CartUpdateDto cartUpdateDto) {
		cartRepositoryPort.updateCartItem(cartUpdateDto);
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


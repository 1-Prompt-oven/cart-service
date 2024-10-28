package com.promptove.cartservice.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promptove.cartservice.application.mapper.CartDtoMapper;
import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.in.CartUseCase;
import com.promptove.cartservice.application.port.out.CartOutportDto;
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
	public void createCart(CartRequestDto cartCreateRequestDto) {

		CartOutportDto cartOutportDto = cartRepositoryPort.getCartByProductUuidAndMemberUuid(
			cartCreateRequestDto.getMemberUuid(), cartCreateRequestDto.getProductUuid()).orElse(null);

		// 예전에 담은적이 없으면
		if (cartOutportDto == null) {

			Cart cart = cartDomainService.createCart(cartCreateRequestDto);

			cartRepositoryPort.save(cartDtoMapper.toDto(cart));
		} else {
			// 예전에 담은적이 있고 삭제 된 상태면
			if (cartOutportDto.isDeleted()) {

				Cart cart = cartDomainService.updateExistCart(cartOutportDto);

				cartRepositoryPort.updateCartItem(cartDtoMapper.toDto(cart));
			}
		}
	}

	@Override
	public List<CartRequestDto> getCart(CartRequestDto cartGetRequestDto) {

		Cart cart = cartDtoMapper.toDomain(cartGetRequestDto);

		List<CartOutportDto> cartOutportDtoList = cartRepositoryPort.getCart(cartDtoMapper.toDto(cart));

		List<Cart> cartList = cartDomainService.getCart(cartOutportDtoList);

		return cartDtoMapper.toDtoList(cartList);
	}

	@Transactional
	@Override
	public void updateCartItem(CartRequestDto cartUpdateRequestDto) {

		CartOutportDto cartOutportDto = cartRepositoryPort.getCartByProductUuidAndMemberUuid(
			cartUpdateRequestDto.getMemberUuid(), cartUpdateRequestDto.getProductUuid()).orElse(null);

		// 장바구니에 없거나 Soft Delete 된 경우
		if (cartOutportDto == null || cartOutportDto.isDeleted()) {
			throw new IllegalArgumentException("해당 제품이 장바구니에 없습니다.");
		} else {
			Cart cart = cartDomainService.updateCart(cartOutportDto, cartUpdateRequestDto);

			cartRepositoryPort.updateCartItem(cartDtoMapper.toDto(cart));
		}
	}

	@Transactional
	@Override
	public void deleteCartItem(CartRequestDto cartDeleteRequestDto) {

		CartOutportDto cartOutportDto = cartRepositoryPort.getCartByProductUuidAndMemberUuid(
			cartDeleteRequestDto.getMemberUuid(), cartDeleteRequestDto.getProductUuid()).orElse(null);

		if (cartOutportDto == null) {
			throw new IllegalArgumentException("해당 제품이 장바구니에 없습니다.");
		} else {
			Cart cart = cartDomainService.deleteCart(cartOutportDto, cartDeleteRequestDto);
			
			cartRepositoryPort.deleteCartItem(cartDtoMapper.toDto(cart));
		}
	}
}


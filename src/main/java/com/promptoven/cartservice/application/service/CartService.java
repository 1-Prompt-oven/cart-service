package com.promptoven.cartservice.application.service;

import com.promptoven.cartservice.application.mapper.CartDtoMapper;
import com.promptoven.cartservice.application.port.in.CartRequestDto;
import com.promptoven.cartservice.application.port.in.CartUseCase;
import com.promptoven.cartservice.application.port.out.CartOutportDto;
import com.promptoven.cartservice.application.port.out.CartRepositoryPort;
import com.promptoven.cartservice.domain.model.Cart;
import com.promptoven.cartservice.domain.service.CartDomainService;
import com.promptoven.cartservice.global.common.response.BaseResponseStatus;
import com.promptoven.cartservice.global.error.BaseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                cartCreateRequestDto.getProductUuid(), cartCreateRequestDto.getMemberUuid()).orElse(null);

        // 예전에 담은적이 없으면
        if (cartOutportDto == null) {

            Cart cart = cartDomainService.createCart(cartCreateRequestDto);

            cartRepositoryPort.save(cartDtoMapper.toCreateDto(cart));
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
                cartUpdateRequestDto.getProductUuid(), cartUpdateRequestDto.getMemberUuid()).orElse(null);

        // 장바구니에 없거나 Soft Delete 된 경우
        if (cartOutportDto == null || cartOutportDto.isDeleted()) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_CART);
        } else {
            Cart cart = cartDomainService.updateCart(cartOutportDto, cartUpdateRequestDto);

            cartRepositoryPort.updateCartItem(cartDtoMapper.toDto(cart));
        }
    }

    @Transactional
    @Override
    public void deleteCartItem(CartRequestDto cartDeleteRequestDto) {

        CartOutportDto cartOutportDto = cartRepositoryPort.getCartByProductUuidAndMemberUuid(
                cartDeleteRequestDto.getProductUuid(), cartDeleteRequestDto.getMemberUuid()).orElse(null);

        if (cartOutportDto == null) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_CART);
        } else {
            Cart cart = cartDomainService.deleteCart(cartOutportDto);

            cartRepositoryPort.deleteCartItem(cartDtoMapper.toDto(cart));
        }
    }
}


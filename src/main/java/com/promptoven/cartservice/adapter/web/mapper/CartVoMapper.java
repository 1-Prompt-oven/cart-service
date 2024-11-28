package com.promptoven.cartservice.adapter.web.mapper;

import com.promptoven.cartservice.adapter.web.vo.CartCreateRequestVo;
import com.promptoven.cartservice.adapter.web.vo.CartDeleteRequestVo;
import com.promptoven.cartservice.adapter.web.vo.CartResponseVo;
import com.promptoven.cartservice.adapter.web.vo.CartUpdateVo;
import com.promptoven.cartservice.application.port.in.dto.CartInportDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartVoMapper {

    public CartInportDto toDto(CartCreateRequestVo cartCreateRequestVo) {
        return CartInportDto.builder()
                .memberUuid(cartCreateRequestVo.getMemberUuid())
                .productUuid(cartCreateRequestVo.getProductUuid())
                .selected(true)
                .deleted(false)
                .build();
    }

    public CartInportDto toGetDto(String memberUuid) {
        return CartInportDto.builder()
                .memberUuid(memberUuid)
                .build();
    }

    public List<CartResponseVo> toVoList(List<CartInportDto> cartInportDtoList) {
        return cartInportDtoList.stream().map(cartRequestDto -> CartResponseVo.builder()
                .id(cartRequestDto.getCartId())
                .memberUuid(cartRequestDto.getMemberUuid())
                .productUuid(cartRequestDto.getProductUuid())
                .selected(cartRequestDto.isSelected())
                .deleted(cartRequestDto.isDeleted())
                .createdDate(cartRequestDto.getCreatedDate())
                .lastModifiedDate(cartRequestDto.getLastModifiedDate())
                .build()).toList();
    }

    public CartInportDto toUpdateDto(CartUpdateVo cartUpdateVo) {
        return CartInportDto.builder()
                .cartId(cartUpdateVo.getCartId())
                .selected(cartUpdateVo.isSelected())
                .build();
    }

    public CartInportDto toDeleteDto(CartDeleteRequestVo cartDeleteRequestVo) {
        return CartInportDto.builder()
                .cartId(cartDeleteRequestVo.getCartId())
                .build();
    }
}

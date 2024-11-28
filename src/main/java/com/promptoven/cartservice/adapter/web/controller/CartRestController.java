package com.promptoven.cartservice.adapter.web.controller;

import com.promptoven.cartservice.adapter.web.mapper.CartVoMapper;
import com.promptoven.cartservice.adapter.web.vo.CartCreateRequestVo;
import com.promptoven.cartservice.adapter.web.vo.CartResponseVo;
import com.promptoven.cartservice.adapter.web.vo.CartUpdateVo;
import com.promptoven.cartservice.application.port.in.dto.CartInportDto;
import com.promptoven.cartservice.application.port.in.usecase.CartUseCase;
import com.promptoven.cartservice.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/member/cart")
// input adapter
public class CartRestController {

    private final CartUseCase cartUseCase;
    private final CartVoMapper cartVoMapper;

    @Operation(summary = "장바구니 생성 API", tags = {"장바구니"})
    @PostMapping
    public BaseResponse<Void> createCart(@RequestBody CartCreateRequestVo cartCreateRequestVo) {

        cartUseCase.createCart(cartVoMapper.toDto(cartCreateRequestVo));

        return new BaseResponse<>();
    }

    @Operation(summary = "장바구니 조회 API", tags = {"장바구니"})
    @GetMapping("/list/{memberUuid}")
    public BaseResponse<List<CartResponseVo>> getCart(@PathVariable String memberUuid) {
        List<CartInportDto> cartInportDtoList = cartUseCase.getCart(cartVoMapper.toGetDto(memberUuid));
        return new BaseResponse<>(cartVoMapper.toVoList(cartInportDtoList));
    }

    @Operation(summary = "장바구니 선택 상태 변경 API", tags = {"장바구니"})
    @PutMapping()
    public BaseResponse<Void> updateCartItem(@RequestBody CartUpdateVo cartUpdateVo) {

        cartUseCase.updateCartItem(cartVoMapper.toUpdateDto(cartUpdateVo));

        return new BaseResponse<>();
    }

    @Operation(summary = "장바구니 항목 삭제 API", tags = {"장바구니"})
    @DeleteMapping({"/{cartId}"})
    public BaseResponse<Void> deleteCartItem(@PathVariable Long cartId) {

        cartUseCase.deleteCartItem(cartVoMapper.toDeleteDto(cartId));

        return new BaseResponse<>();
    }

    @Operation(summary = "회원의 장바구니 담은 유무", tags = {"장바구니"})
    @GetMapping("/exist")
    public BaseResponse<Boolean> isCartExist(@RequestParam String memberUuid, @RequestParam String productUuid) {
        return new BaseResponse<>(cartUseCase.isCartExist(cartVoMapper.toExistDto(productUuid, memberUuid)));
    }
}

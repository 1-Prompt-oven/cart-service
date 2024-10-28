package com.promptove.cartservice.adapter.in.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promptove.cartservice.adapter.in.web.mapper.CartVoMapper;
import com.promptove.cartservice.adapter.in.web.vo.CartCreateRequestVo;
import com.promptove.cartservice.adapter.in.web.vo.CartDeleteRequestVo;
import com.promptove.cartservice.adapter.in.web.vo.CartGetRequestVo;
import com.promptove.cartservice.adapter.in.web.vo.CartResponseVo;
import com.promptove.cartservice.adapter.in.web.vo.CartUpdateVo;
import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.in.CartUseCase;
import com.promptove.cartservice.global.common.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/member/cart")
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
	@PostMapping("/list")
	public BaseResponse<List<CartResponseVo>> getCart(@RequestBody CartGetRequestVo cartGetRequestVo) {
		List<CartRequestDto> cartRequestDtoList = cartUseCase.getCart(cartVoMapper.toGetDto(cartGetRequestVo));
		return new BaseResponse<>(cartVoMapper.toVoList(cartRequestDtoList));
	}

	// @Operation(summary = "장바구니 조회 API", tags = {"장바구니"})
	// @GetMapping
	// public BaseResponse<List<CartResponseVo>> getCart(@RequestHeader("memberUuid") String memberUuid) {
	// 	List<CartRequestDto> cartRequestDtoList = cartUseCase.getCart(
	// 		cartVoMapper.toGetDto(memberUuid));
	// 	return new BaseResponse<>(BaseResponseStatus.SUCCESS, cartVoMapper.toVoList(cartRequestDtoList));
	// }

	@Operation(summary = "장바구니 선택 상태 변경 API", tags = {"장바구니"})
	@PutMapping()
	public BaseResponse<Void> updateCartItem(@RequestBody CartUpdateVo cartUpdateVo) {

		cartUseCase.updateCartItem(cartVoMapper.toUpdateDto(cartUpdateVo));

		return new BaseResponse<>();
	}

	@Operation(summary = "장바구니 항목 삭제 API", tags = {"장바구니"})
	@DeleteMapping()
	public BaseResponse<Void> deleteCartItem(@RequestBody CartDeleteRequestVo cartDeleteRequestVo) {

		cartUseCase.deleteCartItem(cartVoMapper.toDeleteDto(cartDeleteRequestVo));

		return new BaseResponse<>();
	}
}

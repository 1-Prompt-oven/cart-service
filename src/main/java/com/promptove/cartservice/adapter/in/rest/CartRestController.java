package com.promptove.cartservice.adapter.in.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.promptove.cartservice.adapter.in.mapper.CartVoMapper;
import com.promptove.cartservice.adapter.in.rest.vo.CartDeleteRequestVo;
import com.promptove.cartservice.adapter.in.rest.vo.CartRequestVo;
import com.promptove.cartservice.adapter.in.rest.vo.CartResponseVo;
import com.promptove.cartservice.adapter.in.rest.vo.CartUpdateVo;
import com.promptove.cartservice.application.port.in.CartUseCase;
import com.promptove.cartservice.domain.model.Cart;
import com.promptove.cartservice.global.common.response.BaseResponse;
import com.promptove.cartservice.global.common.response.BaseResponseStatus;

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
	public BaseResponse<Void> createCart(@RequestBody CartRequestVo cartRequestVo) {
		cartUseCase.createCart(cartVoMapper.toDto(cartRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "장바구니 조회 API", tags = {"장바구니"})
	@GetMapping
	public ResponseEntity<List<CartResponseVo>> getCart(@RequestParam String memberUuid) {
		List<Cart> carts = cartUseCase.getCart(memberUuid);
		return ResponseEntity.ok(carts.stream().map(cartVoMapper::toResponseVo).toList());
	}

	@Operation(summary = "장바구니 선택 상태 변경 API", tags = {"장바구니"})
	@PutMapping()
	public ResponseEntity<String> updateCartItem(@RequestBody CartUpdateVo cartUpdateVo) {

		cartUseCase.updateCartItem(cartUpdateVo.getMemberUuid(), cartUpdateVo.getProductUuid(),
			cartUpdateVo.isSelected());
		return ResponseEntity.ok("장바구니 선택 상태 변경 성공");
	}

	@Operation(summary = "장바구니 항목 삭제 API", tags = {"장바구니"})
	@DeleteMapping()
	public ResponseEntity<String> deleteCartItem(@RequestBody CartDeleteRequestVo cartDeleteRequestVo) {
		cartUseCase.deleteCartItem(cartDeleteRequestVo.getMemberUuid(), cartDeleteRequestVo.getProductUuid());
		return ResponseEntity.ok("장바구니 항목 삭제 성공");
	}
}

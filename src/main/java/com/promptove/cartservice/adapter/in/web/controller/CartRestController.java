package com.promptove.cartservice.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.promptove.cartservice.adapter.in.mapper.CartVoMapper;
import com.promptove.cartservice.adapter.in.web.dto.CartUpdateDto;
import com.promptove.cartservice.adapter.in.web.vo.CartRequestVo;
import com.promptove.cartservice.adapter.in.web.vo.CartResponseVo;
import com.promptove.cartservice.adapter.in.web.vo.CartUpdateVo;
import com.promptove.cartservice.application.port.in.CartUseCase;
import com.promptove.cartservice.domain.Cart;

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

	@PostMapping
	@Operation(summary = "장바구니 생성 API", tags = {"장바구니"})
	public ResponseEntity<String> createCart(@RequestBody CartRequestVo cartRequestVo) {
		cartUseCase.createCart(cartRequestVo.toDto());
		return ResponseEntity.ok("장바구니 생성 성공");
	}

	@GetMapping
	@Operation(summary = "장바구니 조회 API", tags = {"장바구니"})
	public ResponseEntity<List<CartResponseVo>> getCart(@RequestParam String memberUuid) {
		List<Cart> carts = cartUseCase.getCart(memberUuid);
		return ResponseEntity.ok(carts.stream().map(cartVoMapper::toResponseVo).toList());
	}

	@DeleteMapping("/{productUuid}")
	@Operation(summary = "장바구니 항목 삭제 API", tags = {"장바구니"})
	public ResponseEntity<String> deleteCartItem(@RequestParam String memberUuid, @PathVariable String productUuid) {
		cartUseCase.deleteCartItem(memberUuid, productUuid);
		return ResponseEntity.ok("장바구니 항목 삭제 성공");
	}

	@PutMapping("/{productUuid}")
	@Operation(summary = "장바구니 선택 상태 변경 API", tags = {"장바구니"})
	public ResponseEntity<String> updateCartItem(@PathVariable String productUuid,
		@RequestBody CartUpdateVo cartUpdateVo) {
		CartUpdateDto cartUpdateDto = cartUpdateVo.toDto(productUuid);
		cartUseCase.updateCartItem(cartUpdateDto);
		return ResponseEntity.ok("장바구니 선택 상태 변경 성공");
	}
}

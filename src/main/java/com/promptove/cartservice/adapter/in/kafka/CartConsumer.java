package com.promptove.cartservice.adapter.in.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.promptove.cartservice.application.port.in.CartUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
// Kafka Listener
public class CartConsumer {

	private final CartUseCase cartUseCase;

	// 제품 삭제 이벤트 리스너
	@KafkaListener(topics = "product-delete-event", groupId = "cart-group")
	public void consumeProductDeleteEvent(String productUuid) {
		log.info("Received product delete event for productUuid: {}", productUuid);
		// 제품이 삭제되면 해당 제품을 장바구니에서 소프트 삭제 처리
		cartUseCase.deleteCartItemByProductUuid(productUuid);
	}

	// 결제 성공 이벤트 리스너
	@KafkaListener(topics = "order-success-event", groupId = "cart-group")
	public void consumeOrderSuccessEvent(String memberUuid) {
		log.info("Received order success event for memberUuid: {}", memberUuid);
		// 결제 성공 시 해당 유저의 장바구니를 정리
		cartUseCase.clearCartAfterOrder(memberUuid);
	}
}

package com.promptoven.cartservice.adapter.kafka.in;

import com.promptoven.cartservice.application.mapper.CartDtoMapper;
import com.promptoven.cartservice.application.port.in.dto.RequestMessageDto;
import com.promptoven.cartservice.application.port.out.call.CartRepositoryPort;
import com.promptoven.cartservice.domain.model.Cart;
import com.promptoven.cartservice.domain.service.CartDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class KafkaConsumer {

//    private static final String CREATE_TOPIC = "payment_create_event";
    private final CartRepositoryPort cartRepositoryPort;
    private final CartDomainService cartDomainService;
    private final CartDtoMapper cartDtoMapper;

    public KafkaConsumer(@Qualifier("cartMysqlAdapter") CartRepositoryPort cartRepositoryPort) {
        this.cartRepositoryPort = cartRepositoryPort;
        this.cartDomainService = new CartDomainService();
        this.cartDtoMapper = new CartDtoMapper();
    }

    @KafkaListener(topics = "${payment-create-event}", groupId = "kafka-payment-cart-service")
    @Transactional
    public void consumeCreate(RequestMessageDto message) {

        log.info("consumeCreate: {}", message);

        message.getProductUuids().forEach(productUuid ->
                cartRepositoryPort.getCartByProductUuidAndMemberUuid(
                        productUuid, message.getMemberUuid()
                ).ifPresent(cartOutportDto -> {
                    Cart cart = cartDomainService.deleteCart(cartOutportDto);
                    cartRepositoryPort.deleteCartItem(cartDtoMapper.toDto(cart));
                })
        );
    }
}

package com.promptoven.cartservice.application.port.in;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestMessageDto {
    private Long paymentId;

    private String memberUuid;

    private List<String> productUuid;


}

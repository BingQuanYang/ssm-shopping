package com.smart.shopping.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDto {
    //会员id
    private Long memberId;
    //购物车id
    private Long cartId;
    //商品id
    private Long productId;
    //数量
    private Long quantity;
}

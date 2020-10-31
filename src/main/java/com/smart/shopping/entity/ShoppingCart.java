package com.smart.shopping.entity;

import lombok.Data;

@Data
public class ShoppingCart {
    /**
     *
     */
    private Long cartId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 删除状态:0->未删除;1->已删除
     */
    private Integer deleteStatus;
}


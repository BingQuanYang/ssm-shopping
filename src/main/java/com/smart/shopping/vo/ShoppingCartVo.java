package com.smart.shopping.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCartVo {
    //购物车id
    private Long cartId;
    //商品id
    private Long productId;
    //数量
    private Long quantity;
    //图片
    private String pic;
    //价格
    private BigDecimal price;
    //促销价格
    private BigDecimal promotionPrice;
    //商品描述
    private String description;
    //
}

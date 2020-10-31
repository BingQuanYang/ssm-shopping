package com.smart.shopping.service;

import com.smart.shopping.dto.ShoppingCartDto;
import com.smart.shopping.entity.ShoppingCart;
import com.smart.shopping.vo.ShoppingCartVo;

import java.util.List;

public interface ShoppingCartService {


    /**
     * 根据会员id查询购物信息
     *
     * @param memberId 会员id
     * @return
     */
    List<ShoppingCartVo> list(Long memberId);

    /**
     * 添加购物车
     *
     * @param cartDto
     * @return
     */
    List<ShoppingCartVo> save(ShoppingCartDto cartDto);

    /**
     * 修改购物车商品的数量
     *
     * @param cartDto
     * @return
     */
    List<ShoppingCartVo> update(ShoppingCartDto cartDto);

    /**
     * 根据购物车id删除购物车
     *
     * @param cartIds
     * @return
     */
    List<ShoppingCartVo> delete(List<Long> cartIds);
}

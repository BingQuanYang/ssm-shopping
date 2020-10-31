package com.smart.shopping.mapper;

import com.smart.shopping.bo.ShoppingCartBo;
import com.smart.shopping.entity.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartMapper {
    /**
     * 根据会员id查询购物列表
     *
     * @param memberId
     * @return
     */
    List<ShoppingCart> selectList(@Param("memberId") Long memberId);

    /**
     * 跟据会员id和商品id查询购物信息
     *
     * @param memberId
     * @param productId
     * @return
     */
    ShoppingCart selectByMemberIdAndProductId(@Param("memberId") Long memberId, @Param("productId") Long productId);

    /**
     * 添加购物车
     *
     * @param cart
     * @return
     */
    int insert(@Param("cart") ShoppingCart cart);

    /**
     * 根据购物车id删除购物车信息
     *
     * @param id
     * @return
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据会员id和商品id修改商品的数量
     *
     * @param memberId  会员id
     * @param productId 会员id
     * @param quantity  商品的数量
     * @return
     */
    int updataByMemberIdAndProduceId(@Param("memberId") Long memberId, @Param("productId") Long productId, @Param("quantity") Long quantity);
}

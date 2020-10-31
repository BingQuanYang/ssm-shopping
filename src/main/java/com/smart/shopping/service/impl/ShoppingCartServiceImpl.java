package com.smart.shopping.service.impl;

import com.smart.shopping.bo.ShoppingCartBo;
import com.smart.shopping.dto.ShoppingCartDto;
import com.smart.shopping.entity.Product;
import com.smart.shopping.entity.ShoppingCart;
import com.smart.shopping.exception.ServiceException;
import com.smart.shopping.mapper.ProductMapper;
import com.smart.shopping.mapper.ShoppingCartMapper;
import com.smart.shopping.service.ShoppingCartService;
import com.smart.shopping.utils.ColaBeanUtils;
import com.smart.shopping.utils.ErrorStatus;
import com.smart.shopping.vo.ShoppingCartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    ShoppingCartMapper shoppingCartMapper;
    @Resource
    ProductMapper productMapper;

    /**
     * 根据会员id查询购物信息
     *
     * @param memberId 会员id
     * @return
     */
    @Override
    public List<ShoppingCartVo> list(Long memberId) {
        List<ShoppingCartVo> cartVos = null;
        try {
            //通过购物车会员id获取购物车信息
            List<ShoppingCart> carts = shoppingCartMapper.selectList(memberId);
            //将购物车信息转换成Vo
            cartVos = ColaBeanUtils.copyListProperties(carts, ShoppingCartVo::new);

            cartVos.forEach(cartVo -> {
                //通过商品id获取商品的详细信息
                Product product = productMapper.selectById(cartVo.getProductId());
                //将商品信息转换成Vo
                BeanUtils.copyProperties(product, cartVo);
            });
        } catch (Exception e) {
            new ServiceException(ErrorStatus.SERVICE_ERROR.getMessage(), ErrorStatus.SERVICE_ERROR.getStatus());
        }

        return cartVos;
    }

    /**
     * 添加购物车
     *
     * @param cartDto
     * @return
     */
    @Override
    public List<ShoppingCartVo> save(ShoppingCartDto cartDto) {
        try {
            //通过会员id和商品id获取购物车信息
            ShoppingCart cart = shoppingCartMapper.selectByMemberIdAndProductId(cartDto.getMemberId(), cartDto.getProductId());
            //判断购物车信息是否为空   为空表示不存在该条记录
            if (cart == null) {
                //保存操作
                cart = new ShoppingCart();
                BeanUtils.copyProperties(cartDto, cart);
                shoppingCartMapper.insert(cart);
            } else {
                //修改商品的数量
                update(cartDto);
            }
        } catch (Exception e) {
            new ServiceException(ErrorStatus.SERVICE_ERROR.getMessage(), ErrorStatus.SERVICE_ERROR.getStatus());
        } finally {
            return list(cartDto.getMemberId());
        }
    }

    /**
     * 修改购物车商品的数量
     *
     * @param cartDto
     * @return
     */
    @Override
    public List<ShoppingCartVo> update(ShoppingCartDto cartDto) {
        try {
            //通过会员id和商品id获取购物车信息
            ShoppingCart cart = shoppingCartMapper.selectByMemberIdAndProductId(cartDto.getMemberId(), cartDto.getProductId());
            //判断购物车信息是否不为空
            if (cart != null) {
                //计算购物车需要修改的数量     原有数量+添加数量
                long quantity = cart.getQuantity() + cartDto.getQuantity();
                //判断数量是否大于等1 是则更新操作 否则删除该条购物车信息
                if (quantity >= 1) {
                    shoppingCartMapper.updataByMemberIdAndProduceId(cart.getMemberId(), cart.getProductId(), quantity);
                } else {
                    List<Long> list = new ArrayList<>();
                    list.add(cart.getCartId());
                    delete(list);
                }
            }
        } catch (Exception e) {
            new ServiceException(ErrorStatus.SERVICE_ERROR.getMessage(), ErrorStatus.SERVICE_ERROR.getStatus());
        }
        return list(cartDto.getMemberId());
    }

    /**
     * 根据购物车id删除购物车
     *
     * @param cartIds
     * @return
     */
    @Override
    public List<ShoppingCartVo> delete(List<Long> cartIds) {
        try {
            cartIds.forEach(id -> {
                //根据购物车id删除购物车信息
                shoppingCartMapper.deleteById(id);
            });
        } catch (Exception e) {
            new ServiceException(ErrorStatus.SERVICE_ERROR.getMessage(), ErrorStatus.SERVICE_ERROR.getStatus());
        }
        return null;
    }
}

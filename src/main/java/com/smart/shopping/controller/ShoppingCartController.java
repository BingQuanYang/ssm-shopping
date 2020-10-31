package com.smart.shopping.controller;

import com.smart.shopping.dto.ShoppingCartDto;
import com.smart.shopping.service.ShoppingCartService;
import com.smart.shopping.utils.ResponseEntity;
import com.smart.shopping.vo.ShoppingCartVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车功能
 * 1.查询购物车
 * 2.添加购物车
 * 3.删除购物车
 * 4.修改商品的数量
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Resource
    ShoppingCartService cartService;

    /**
     * 根据会员id查询购物车信息
     *
     * @param memberId 会员id
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity list(Long memberId) {
        try {
            List<ShoppingCartVo> data = cartService.list(memberId);
            return ResponseEntity.success(data);
        } catch (Exception e) {
            return ResponseEntity.error();
        }
    }

    /**
     * 添加购物车
     *
     * @param cartDto
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity add(ShoppingCartDto cartDto) {
        try {
            List<ShoppingCartVo> data = cartService.save(cartDto);
            return ResponseEntity.success(data);
        } catch (Exception e) {
            return ResponseEntity.error();
        }
    }

    /**
     * 删除购物车
     *
     * @param cartIds
     * @return
     */
    @PostMapping("/del")
    public ResponseEntity delete(@RequestParam List<Long> cartIds) {
        try {
            List<ShoppingCartVo> data = cartService.delete(cartIds);
            return ResponseEntity.success(data);
        } catch (Exception e) {
            return ResponseEntity.error();
        }
    }

    /**
     * 修改商品数量
     *
     * @param cartDto
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity update(ShoppingCartDto cartDto) {
        try {
            List<ShoppingCartVo> data = cartService.update(cartDto);
            return ResponseEntity.success(data);
        } catch (Exception e) {
            return ResponseEntity.error();
        }
    }
}

package com.smart.shopping.mapper;

import com.smart.shopping.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    Product selectById(@Param("id") Long id);
}

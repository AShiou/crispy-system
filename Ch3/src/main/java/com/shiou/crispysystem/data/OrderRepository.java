package com.shiou.crispysystem.data;

import com.shiou.crispysystem.Order;

/**
 * TODO
 *
 * @author shiou
 * @version 1.0.0
 * @since 2020/07/23 09:17
 */
public interface OrderRepository {
  Order save(Order order);
}

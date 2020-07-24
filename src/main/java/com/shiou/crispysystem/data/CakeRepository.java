package com.shiou.crispysystem.data;

import com.shiou.crispysystem.Cake;

/**
 * TODO
 *
 * @author shiou
 * @version 1.0.0
 * @since 2020/07/23 09:16
 */
public interface CakeRepository {
  Cake save(Cake decorate);
}

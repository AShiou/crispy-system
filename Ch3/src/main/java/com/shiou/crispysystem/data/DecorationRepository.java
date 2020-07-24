package com.shiou.crispysystem.data;

import com.shiou.crispysystem.Decoration;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author shiou
 * @version 1.0.0
 * @since 2020/07/22 17:01
 */
public interface DecorationRepository {
  Iterable<Decoration> findAll();
  Decoration findOne(String id);
  Decoration save(Decoration decoration);
}

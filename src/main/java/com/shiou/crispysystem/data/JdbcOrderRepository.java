package com.shiou.crispysystem.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shiou.crispysystem.Cake;
import com.shiou.crispysystem.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author shiou
 * @version 1.0.0
 * @since 2020/07/23 10:57
 */
@Repository
public class JdbcOrderRepository implements OrderRepository {
  private SimpleJdbcInsert orderInserter;
  private SimpleJdbcInsert orderCakeInserter;
  private ObjectMapper objectMapper;

  @Autowired
  public JdbcOrderRepository(JdbcTemplate jdbc) {
    this.orderInserter = new SimpleJdbcInsert(jdbc)
        .withTableName("Cake_Order")
        .usingGeneratedKeyColumns("id");

    this.orderCakeInserter = new SimpleJdbcInsert(jdbc)
        .withTableName("Cake_Order_Cakes");

    this.objectMapper = new ObjectMapper();
  }

  @Override
  public Order save(Order order) {
    order.setPlacedAt(new Date());
    long orderId = saveOrderDetails(order);
    order.setId(orderId);
    List<Cake> cakes = order.getCakes();
    for (Cake cake: cakes) {
      saveCakeToOrder(cake, orderId);
    }
    return order;
  }

  private long saveOrderDetails(Order order) {
    @SuppressWarnings("unchecked")
    Map<String, Object> values = objectMapper.convertValue(order, Map.class);
    values.put("placedAt", order.getPlacedAt());

    long orderId = orderInserter
            .executeAndReturnKey(values)
            .longValue();
    return orderId;
  }

  private void saveCakeToOrder(Cake cake, long orderId) {
    Map<String, Object> values = new HashMap<>();
    values.put("cakeOrder", orderId);
    values.put("cake", cake.getId());
    orderCakeInserter.execute(values);
  }
}

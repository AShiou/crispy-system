package com.shiou.crispysystem.data;

import com.shiou.crispysystem.Cake;
import com.shiou.crispysystem.Decoration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

/**
 * TODO
 *
 * @author shiou
 * @version 1.0.0
 * @since 2020/07/23 09:19
 */
@Repository
public class JdbcCakeRepository implements CakeRepository {
  private JdbcTemplate jdbc;

  public JdbcCakeRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Cake save(Cake cake) {
    long cakeId = saveCakeInfo(cake);
    cake.setId(cakeId);
    for (Decoration decoration: cake.getDecorations()) {
      saveDecorationToCake(decoration, cakeId);
    }
    return cake;
  }

  private long saveCakeInfo(Cake cake) {
    cake.setCreatedAt(new Date());
    PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
        "insert into Cake(name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP)
        .newPreparedStatementCreator(
            Arrays.asList(
                cake.getName(),
                new Timestamp(cake.getCreatedAt().getTime())
            )
        );
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbc.update(psc, keyHolder);
    return keyHolder.getKey().longValue();
  }

  private void saveDecorationToCake(Decoration decoration, long cakeId) {
    jdbc.update("insert into Cake_Decorations (cake, decoration) values (?, ?)", cakeId, decoration.getId());
  }
}

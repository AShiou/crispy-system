package com.shiou.crispysystem.data;

import com.shiou.crispysystem.Decoration;
import com.shiou.crispysystem.data.DecorationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO
 *
 * @author shiou
 * @version 1.0.0
 * @since 2020/07/22 17:12
 */
@Repository
public class JdbcDecorationRepository implements DecorationRepository {
  private JdbcTemplate jdbc;

  @Autowired
  public JdbcDecorationRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Iterable<Decoration> findAll() {
    return jdbc.query("select id, name, type from Decoration", this::mapRowToDecoration);
  }

  @Override
  public Decoration findOne(String id) {
    return jdbc.queryForObject("select id, name, type from Decoration where id = ?", this::mapRowToDecoration, id);
  }

  @Override
  public Decoration save(Decoration decoration) {
    jdbc.update("insert into Decoration (id, name, type) values (?, ?, ?)", decoration.getId(), decoration.getName(), decoration.getType().toString());
    return decoration;
  }

  private Decoration mapRowToDecoration(ResultSet rs, int rowNum) throws SQLException {
    return new Decoration(rs.getString("id"), rs.getString("name"), Decoration.Type.valueOf(rs.getString("type")));
  }
}

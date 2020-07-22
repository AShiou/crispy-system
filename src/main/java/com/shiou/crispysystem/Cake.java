package com.shiou.crispysystem;

import lombok.Data;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TODO
 *
 * @author shiou
 * @version 1.0.0
 * @since 2020/07/21 13:04
 */
@Data
public class Cake {
  private Long id;
  private Date createdAt;
  //@NotNull
  //@Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;
  //@Size(min= 1, message = "You must choose at least 1 decoration")
  private List<String> decorations;
}

package com.shiou.crispysystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 *
 * @author shiou
 * @version 1.0.0
 * @since 2020/07/15 17:45
 */
@Controller
public class HomeController {
  @GetMapping("/")
  public String home() {
    return "home";
  }
}

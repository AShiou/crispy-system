package com.shiou.crispysystem;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * TODO
 *
 * @author shiou
 * @version 1.0.0
 * @since 2020/07/21 14:32
 */
@Data
public class Order {
  @NotBlank(message = "Name is required")
  private String name;
  @NotBlank(message = "Street is required")
  private String street;
  @NotBlank(message = "City is required")
  private String city;
  @NotBlank(message = "State is required")
  private String state;
  @NotBlank(message = "Zip is required")
  private String zip;
  @CreditCardNumber(message = "Not a valid credit card number")
  private String ccNumber;
  @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$")
  private String ccExpiration;
  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  private String ccCVV;
}
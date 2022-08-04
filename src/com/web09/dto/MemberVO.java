package com.web09.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class MemberVO {
   private String name;
   private String userid;
   private String pwd;
   private String email;
   private String phone;
   private int admin;
}
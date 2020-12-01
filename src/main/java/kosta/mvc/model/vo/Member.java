package kosta.mvc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
  private String id;
  private String password;
  private String name;
  private String email;
  private String userType;//0 or 1
}

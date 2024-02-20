package site.greenwave.config;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserInfoDto {
    private String role;
    private String name;
    private Integer id;
}

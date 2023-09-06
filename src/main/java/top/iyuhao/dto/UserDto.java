package top.iyuhao.dto;

import lombok.Data;
import top.iyuhao.entity.User;

import java.io.Serializable;

/**
 * @author yuhao
 * @date 2023/8/23
 **/
@Data
public class UserDto implements Serializable {
    private User user;
    private String token;
}

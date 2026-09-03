package cn.grassit.grassitbackend.Database.Rows;

import lombok.Data;

@Data
public class User {
    public Long id;
    public String username;
    public String nickname;
    public String password_hash;
    public String email;
    public String avatar;
}

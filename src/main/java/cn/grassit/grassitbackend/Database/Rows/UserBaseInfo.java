package cn.grassit.grassitbackend.Database.Rows;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseInfo {
    public Long id;
    public String username;
    public String nickname;
    public String password_hash;
    public String email;
    public String avatar;
}

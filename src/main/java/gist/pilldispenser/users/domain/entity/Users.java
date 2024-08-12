package gist.pilldispenser.users.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gist.pilldispenser.common.entity.BaseEntity;
import gist.pilldispenser.common.entity.enums.RoleType;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users extends BaseEntity {

    private String email;
    private String password; // kakao user info의 id(long)를 암호화한 것
    private String profileImage;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 하드웨어 번호
    @Setter
    private String hardwareNo;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserDrugInfo> userDrugInfos;

}

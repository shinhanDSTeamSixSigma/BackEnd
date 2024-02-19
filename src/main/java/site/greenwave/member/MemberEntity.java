package site.greenwave.member;

import jakarta.persistence.*;
import lombok.*;
import site.greenwave.farm.entity.FarmEntity;

@Entity
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_member")
@EqualsAndHashCode(of="memberNo")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String phone;
    private String nickname;
    private String address1;
    private String address2;
    private Integer memberPoint;
    private String zipcode;
    
/*    @Column
    private String member_role;*/

//    @OneToOne(mappedBy = "memberEntity", cascade = CascadeType.ALL)
//    private FarmEntity farmEntity;

}
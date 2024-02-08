package site.greenwave.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import site.greenwave.farm.FarmEntity;

@Entity
@Setter
@Getter
@Table(name = "tb_member")
@EqualsAndHashCode(of="memberNo")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String phone;
    private String nickname;
    private String address1;
    private String address2;
    private int memberPoint;
    private String zipcode;
    
/*    @Column
    private String member_role;*/
    
    @OneToOne(mappedBy = "memberEntity")
    private FarmEntity farmEntity;

}
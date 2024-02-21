package site.greenwave.farm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import site.greenwave.member.entity.MemberEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString(exclude = "memberEntity")
@Table(name = "tb_farm")
@EqualsAndHashCode(of = "farmNo")
public class FarmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer farmNo;
    // 농장 이름
    private String farmName;
    private String farmAddress;
    private String farmContent;
    private String farmDescription;
    private String farmPhone;
    private String farmSize;
    private String farmCareer;
    private Integer farmOrderNum;
    private String farmConnect;
    private String farmCategory;
    private Double farmRating;
    private Integer reviewCnt;
    @OneToOne
    @JoinColumn(name = "member_no")
    private MemberEntity memberEntity;


}
package site.greenwave.farm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import site.greenwave.member.MemberEntity;

@Entity
@Setter
@Getter
@Table(name = "tb_farm_like")
public class FarmLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer farmLikeNo;

    @ManyToOne
    @JoinColumn(name = "farm_no")
    private FarmEntity farmEntity;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private MemberEntity memberEntity;
}

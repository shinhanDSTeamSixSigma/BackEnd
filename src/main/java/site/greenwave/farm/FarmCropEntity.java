package site.greenwave.farm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import site.greenwave.dict.CropDictEntity;
import site.greenwave.member.MemberEntity;

@Entity
@Setter
@Getter
@Table(name = "tb_farm_crop")
@EqualsAndHashCode(of = "farmCropNo")
public class FarmCropEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer farmCropNo;

    @ManyToOne
    @JoinColumn(name = "farm_no")
    private FarmEntity farmEntity;
    @OneToOne
    @JoinColumn(name = "crop_dict_no")
    private CropDictEntity cropDictEntity;
}

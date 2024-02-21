package site.greenwave.farm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.greenwave.dict.entity.CropDictEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "tb_farm_crop")
@EqualsAndHashCode(of = "farmCropNo")
public class FarmCropEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer farmCropNo;

    @ManyToOne
    @JoinColumn(name = "farm_no")
    private FarmEntity farmEntity;

    @OneToOne
    @JoinColumn(name = "cropDictNo")
    private CropDictEntity cropDictEntity;

}

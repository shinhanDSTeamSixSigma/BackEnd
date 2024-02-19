package site.greenwave.farm.entity;

import jakarta.persistence.*;
import lombok.*;
import site.greenwave.dict.CropDictEntity;

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

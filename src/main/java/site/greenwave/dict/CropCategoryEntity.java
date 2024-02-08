package site.greenwave.dict;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.greenwave.board.ReviewEntity;

@Getter
@Setter
@ToString 
@Entity
@Table(name="tb_crop_category")
@EqualsAndHashCode(of="cropCateNo")
public class CropCategoryEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cropCateNo;
	private String cropCateName;
}

package site.greenwave.dict;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@Entity
@Table(name="tb_crop_dict")
@EqualsAndHashCode(of="cropDictNo")
public class CropDictEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cropDictNo;
	private int spring;
	private int summer;
	private int fall;
	private int winter;
	private String summary;
	private int term;
	private String cropContent;
	private String tip;
	private String effect;
	private String nutrient;
	private int lowTemp;
	private int highTemp;
	
	@ManyToOne
	@JoinColumn(name = "tb_crop_category_cropCateNo")
	private CropCategoryEntity cropCategoryEntity;
}

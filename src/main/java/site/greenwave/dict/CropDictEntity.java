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
	private Integer cropDictNo;
	private String cropName;
	private Integer spring;
	private Integer summer;
	private Integer fall;
	private Integer winter;
	private String summary;
	private Integer term;
	private String cropContent;
	private String tip;
	private String effect;
	private String nutrient;
	private Integer lowTemp;
	private Integer highTemp;
	
	@ManyToOne
	@JoinColumn(name = "crop_cate_no")
	private CropCategoryEntity cropCategoryEntity;
	
}

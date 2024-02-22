package site.greenwave.dict.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.greenwave.dict.entity.CropCategoryEntity;
import site.greenwave.dict.entity.CropDictEntity;

@Getter
@Setter
@ToString

public class CropDictDTO {
	private Integer cropDictNo;
	private String cropName;
	private Integer spring;
	private Integer summer;
	private Integer fall;
	private Integer winter;
	private String summary;
	private Integer level;
	private Integer term;
	private String cropContent;
	private String tip;
	private String effect;
	private String nutrient;
	private Integer lowTemp;
	private Integer highTemp;
	private CropCategoryEntity cropCategoryEntity;
	private String image;
	
	public CropDictDTO(CropDictEntity entity) {
		this.cropDictNo = entity.getCropDictNo();
		this.cropName = entity.getCropName();
		this.spring = entity.getSpring();
		this.summer = entity.getSummer();
		this.fall = entity.getFall();
		this.winter = entity.getWinter();
		this.summary = entity.getSummary();
		this.term = entity.getTerm();
		this.cropContent = entity.getCropContent();
		this.tip = entity.getTip();
		this.effect = entity.getEffect();
		this.nutrient = entity.getNutrient();
		this.lowTemp = entity.getLowTemp();
		this.highTemp = entity.getHighTemp();
		this.cropCategoryEntity = entity.getCropCategoryEntity();
	
	}
}

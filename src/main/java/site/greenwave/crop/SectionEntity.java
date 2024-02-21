package site.greenwave.crop;

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
import site.greenwave.farm.entity.FarmEntity;
@Entity
@Getter
@Setter
@Table(name="tb_section")
@EqualsAndHashCode(of="sectionNo")
public class SectionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sectionNo;
	private Integer sectionSize;
	
	@ManyToOne
	@JoinColumn(name="farm_no")
	private FarmEntity farmEntity;
}

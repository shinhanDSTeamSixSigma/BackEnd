package site.greenwave.crop;


import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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

@Entity
@Getter
@Setter
@Table(name="tb_crop_maure_log")
@EqualsAndHashCode(of="manureLogNo")
public class CropManureLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer manureLogNo;
	private Integer manureDiv;
	private String manureLog;
	@CreationTimestamp
	private Timestamp createdDate;
	
	@ManyToOne
	@JoinColumn(name="crop_no")
	private CropEntity cropEntity;
}

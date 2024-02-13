package site.greenwave.crop;


import java.sql.Timestamp;

import jakarta.persistence.Column;
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
	private int manureLogNo;
	@Column
	private int manureDiv;
	@Column
	private String manureLog;
	@Column
	private Timestamp createdDate;
	@ManyToOne
	@JoinColumn(name="tb_crop_cropNo")
	private CropEntity cropEntity;
}

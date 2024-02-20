package site.greenwave.crop;


import java.sql.Timestamp;

<<<<<<< HEAD
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
=======
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
>>>>>>> branch 'main' of https://github.com/shinhanDSTeamSixSigma/BackEnd.git
	private CropEntity cropEntity;
}

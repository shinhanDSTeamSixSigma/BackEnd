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
@Table(name="tb_crop_sensor_log")
@EqualsAndHashCode(of="logNo")
public class CropSenseorLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logNo;
	@Column
	private int thomer;
	@Column
	private int humidity;
	@Column
	private int lumen;
	@Column
	private int soilHumid;
	@Column
	private Timestamp sensorTime;
	@ManyToOne
	@JoinColumn(name="tb_crop_cropNo")
	private CropEntity cropEntity;
}

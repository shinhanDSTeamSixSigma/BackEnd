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
@Table(name="tb_crop_sensor_log_detail")
@EqualsAndHashCode(of="logNo")
public class CropSensorLogEntityDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer logNo;
	private Integer thomer;
	private Integer humidity;
	private Integer lumen;
	private Integer soilHumid;
	
	@CreationTimestamp
	private Timestamp sensorTime;
	@ManyToOne
	@JoinColumn(name="crop_no")
	private CropEntity cropEntity;
}

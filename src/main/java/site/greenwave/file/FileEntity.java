package site.greenwave.file;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tb_file")
@EqualsAndHashCode(of="file_no")
public class FileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fileNo;
	private String fileName;
	private String fileSrc;
	private String fileExtension;
	private String manageDiv;
	private Integer fileManageNo;
	@CreationTimestamp
	private Timestamp uploadDate;
}

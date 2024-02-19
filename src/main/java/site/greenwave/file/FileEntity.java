package site.greenwave.file;

import java.sql.Timestamp;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

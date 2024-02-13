package site.greenwave.file;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tb_file")
public class FileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long file_no;
	@Column
	String file_src;
	@Column
	Timestamp upload_date;
	@Column
	String file_extension;
	@Column
	String manage_div;
	@Column
	Long file_manage_no;
}

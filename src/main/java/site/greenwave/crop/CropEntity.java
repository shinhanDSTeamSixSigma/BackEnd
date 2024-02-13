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
@Table(name="tb_crop")
@EqualsAndHashCode(of="cropNo")
public class CropEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cropNo;
	@Column
	private String cropNickname;
	@Column
	private String streamingAddr;
	@ManyToOne
	@JoinColumn(name="tb_section_sectionNo")
	private SectionEntity sectionEntity;
	@Column
	@ManyToOne
	@JoinColumn(name="tb_member_memberNo")
	private MemberEntity memberEntity;
	@Column
	@ManyToOne
	@JoinColumn(name="tb_crop_dict_dictNo")
	private CropDictEntity cropDictEntity;
	@Column
	private Timestamp buyDate;
	@Column
	private Timestamp endDate;
	@Column
	private Timestamp createdDate;
	@Column
	private int cropState;
}
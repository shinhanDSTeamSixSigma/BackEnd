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
import site.greenwave.dict.CropDictEntity;
import site.greenwave.member.entity.MemberEntity;
@Entity
@Getter
@Setter
@Table(name="tb_crop")
@EqualsAndHashCode(of="cropNo")
public class CropEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cropNo;
	private String cropNickname;
	private String streamingAddr;	
	private Integer cropState;
	@CreationTimestamp
	private Timestamp buyDate;
	@CreationTimestamp
	private Timestamp endDate;
	@CreationTimestamp
	private Timestamp createdDate;
	
	@ManyToOne
	@JoinColumn(name="section_no")
	private SectionEntity sectionEntity;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	private MemberEntity memberEntity;
	
	@ManyToOne
	@JoinColumn(name="dict_no")
	private CropDictEntity cropDictEntity;
>>>>>>> branch 'main' of https://github.com/shinhanDSTeamSixSigma/BackEnd.git
}
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
import site.greenwave.dict.entity.CropDictEntity;
import site.greenwave.member.MemberEntity;
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
}
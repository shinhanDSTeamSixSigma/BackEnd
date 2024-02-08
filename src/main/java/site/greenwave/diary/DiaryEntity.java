package site.greenwave.diary;

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
import lombok.ToString;
import site.greenwave.crop.CropEntity;
import site.greenwave.member.MemberEntity;

@Setter
@Getter
@ToString
@Entity
@Table(name="tbl_diary")
@EqualsAndHashCode(of="diaryNo")
public class DiaryEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int diaryNo;
	@CreationTimestamp
	private Timestamp diaryDate;
	private String content;
	@CreationTimestamp
	private Timestamp registerDate;
	
	@ManyToOne
	@JoinColumn(name = "tb_member_memberNo")
	private MemberEntity memberEntity;
	
	@ManyToOne
	@JoinColumn(name = "tb_crop_cropNo")
	private CropEntity cropEntity;
}

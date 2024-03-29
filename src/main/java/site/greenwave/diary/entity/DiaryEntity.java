package site.greenwave.diary.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
import site.greenwave.member.entity.MemberEntity;

@Setter
@Getter
@ToString
@Entity
@Table(name="tb_diary")
@EqualsAndHashCode(of="diaryNo")
public class DiaryEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer diaryNo;
	@CreatedDate
	private Timestamp diaryDate;
	private String content;
	@CreationTimestamp
	private Timestamp registerDate;
	
	@ManyToOne
	@JoinColumn(name = "member_no")
	private MemberEntity memberEntity;
	
	@ManyToOne
	@JoinColumn(name = "crop_no")
	private CropEntity cropEntity;
}

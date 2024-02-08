package site.greenwave.point;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name="tbl_point")
@EqualsAndHashCode(of="pointNo")
public class PointEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pointNo;
	private int pointValue;
	@CreationTimestamp
	private Timestamp pointDate;
	private int changeValue;
	private int changeCause;
	
	@ManyToOne
	@JoinColumn(name = "tb_member_memberNo")
	private MemberEntity memberEntity;
	
	@ManyToOne
	@JoinColumn(name = "tb_crop_cropNo")
	private CropEntity cropEntity;
	
	@OneToOne
	// 아래의 name으로 테이블의 fk를 관리한다!
	@JoinColumn(name = "tb_bill_billNo")
	private BillEntity billEntity;
}
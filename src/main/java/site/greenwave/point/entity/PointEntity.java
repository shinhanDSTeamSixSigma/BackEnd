package site.greenwave.point.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import site.greenwave.member.entity.MemberEntity;

@Setter
@Getter
@ToString
@Entity
@Table(name="tb_point")
@EqualsAndHashCode(of="pointNo")
public class PointEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pointNo;
	private Integer pointValue;
	@CreationTimestamp
	private Timestamp pointDate;
	private Integer changeValue;
	private Integer changeCause;
	
	@ManyToOne
	@JoinColumn(name = "member_no")
	private MemberEntity memberEntity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "crop_no")
	private CropEntity cropEntity;
	
	// 아래의 name으로 테이블의 fk를 관리한다!
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "bill_no")
	private BillEntity billEntity;
}
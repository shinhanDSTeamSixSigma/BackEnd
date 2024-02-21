package site.greenwave.point.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
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
import site.greenwave.member.entity.MemberEntity;

@Setter
@Getter
@Entity
@Table(name="tb_bill")
@EqualsAndHashCode(of="billNo")
public class BillEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billNo;
	private Integer finalValue;
	private Integer originValue;
	private Integer discountValue;
	@CreationTimestamp
	private Timestamp billDate;
	private Integer billDiv;
	
	@ManyToOne
	@JoinColumn(name = "member_no")
	private MemberEntity memberEntity;

//	// joincolum을 식별하기 위함 _ fk 관리하고 있다는걸 확인하는 부분
//	// 양방향이 됨
	// PointEntity 코드에서의 49줄이랑 연결한 것
	@OneToOne(mappedBy = "billEntity", cascade = CascadeType.ALL)
	private PointEntity pointEntity;
	
	// 한쪽에서만 식별할거면 단방향이여도됨
	// 회원은 카드를 알아야되는데 카드는 회원을 몰라도 되니까 그럴떈 단방향인거임
}


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
import site.greenwave.member.MemberEntity;

@Setter
@Getter
@Entity
@Table(name="tb_bill")
@EqualsAndHashCode(of="billNo")
public class BillEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billNo;
	private int finalValue;
	private int firstValue;
	private int discountValue;
	@CreationTimestamp
	private Timestamp billDate;
	private int billDiv;
	
//	@ManyToOne
//	@JoinColumn(name = "tb_member_member_no")
//	private MemberEntity memberEntity;

//	// joincolum을 식별하기 위함 _ fk 관리하고 있다는걸 확인하는 부분
//	// 양방향이 됨
//	// PointEntity 코드에서의 49줄이랑 연결한 것
//	@OneToOne(mappedBy = "billEntity")
//	private PointEntity pointEntity;
	
	// 한쪽에서만 식별할거면 단방향이여도됨
	// 회원은 카드를 알아야되는데 카드는 회우너을 몰라도 되니까 그럴떈 단방향인거임
	
}

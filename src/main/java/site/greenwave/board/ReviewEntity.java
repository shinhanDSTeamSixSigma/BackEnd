package site.greenwave.board;

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
import site.greenwave.farm.FarmEntity;
import site.greenwave.member.MemberEntity;

@Getter
@Setter
@ToString
@Entity
@Table(name="tb_review")
@EqualsAndHashCode(of="reviewNo")
public class ReviewEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reviewNo;
	private String reviewContent;
	private int rating;
	@CreationTimestamp
	private Timestamp createdDate;
	private boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "tb_member_member_no")
	private MemberEntity memberEntity;
	
	@ManyToOne
	@JoinColumn(name = "tb_farm_farm_no")
	private FarmEntity farmEntity;
}

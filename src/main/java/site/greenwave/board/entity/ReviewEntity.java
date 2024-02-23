package site.greenwave.board.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import site.greenwave.farm.entity.FarmEntity;
import site.greenwave.member.entity.MemberEntity;

@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name="tb_review")
@EqualsAndHashCode(of="reviewNo")
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer reviewNo;
	private String reviewContent;
	private Integer rating;
	@CreationTimestamp
	private Timestamp createdDate;
	private boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "member_no")
	private MemberEntity memberEntity;
	
	@ManyToOne
	@JoinColumn(name = "farm_no")
	private FarmEntity farmEntity;
}

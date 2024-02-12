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
import site.greenwave.diary.DiaryEntity;
import site.greenwave.farm.FarmEntity;
import site.greenwave.member.MemberEntity;

@Getter
@Setter
@ToString 
@Entity
@Table(name="tb_board")
@EqualsAndHashCode(of="boardNo")
public class BoardEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int boardNo;
	private int categoryNo;
	private String title;
	private String boardContent;
	@CreationTimestamp
	private Timestamp createdDate;
	private Integer views;
	private boolean isReplied;
	private boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "member_no")
	private MemberEntity memberEntity;
	
	@ManyToOne
	@JoinColumn(name = " farm_no")
	private FarmEntity farmEntity;
}

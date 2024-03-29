package site.greenwave.board.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import site.greenwave.member.entity.MemberEntity;

@Getter
@Setter
@ToString
@Entity
@Table(name="tb_comment")
@EqualsAndHashCode(of="commentNo")
public class CommentEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer commentNo;
	private String commentContent;
	//
	private Integer commentCate;
	private Integer postNo;
	private Timestamp commentDate; 
	@ManyToOne
	@JoinColumn(name = "member_no")
	private MemberEntity memberEntity;
}

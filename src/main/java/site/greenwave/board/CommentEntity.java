package site.greenwave.board;

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
import site.greenwave.member.MemberEntity;

@Getter
@Setter
@ToString
@Entity
@Table(name="tb_comment")
@EqualsAndHashCode(of="commentNo")
public class CommentEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentNo;
	private String commentContent;
	private int commentCate;
	private int postNo;
	
	@ManyToOne
	@JoinColumn(name = "tb_member_memberNo")
	private MemberEntity memberEntity;
}

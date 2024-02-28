package site.greenwave.board.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	
	private int commentNo;
	private String commentContent;
	private int commentCate;
	private int postNo;
	private Timestamp commentDate;
	private int memberNo;
	private String memberId;
	private String nickname;
}

package site.greenwave.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import site.greenwave.board.entity.BoardEntity;
import site.greenwave.board.repository.BoardRepository;
import site.greenwave.farm.FarmEntity;
import site.greenwave.member.MemberEntity;

@SpringBootTest
@Slf4j
@Log4j2
@Commit
public class BoardRepoTest {
	
	@Autowired
	private BoardRepository boardrepo;
	
	@Test
	public void testInsert() throws Exception{
		log.info("---------");
		MemberEntity memberEntity = new MemberEntity(); 
	    memberEntity.setMemberNo(1);
	    FarmEntity farmEntity = new FarmEntity();
	    farmEntity.setFarmNo(1);    
        for (int i = 1; i < 11; i++) {
            BoardEntity boardEntity = BoardEntity.builder()
                    .boardNo(i)
                    .categoryNo(1)
            		.title("문의 제목")
            		.boardContent("문의 내용입니당당다람쥐")       
            		.views(0)
            		.isReplied(false)
            		.isDeleted(false)
            		.memberEntity(memberEntity)
//            		.farmEntity(farmEntity)
            		.build();
            boardrepo.save(boardEntity);
        }
	}
	
	@Test
	public void testRead1() throws Exception{
		int memberNo=1;
		int categoryNo=1;
		
		List<BoardEntity> boardList=boardrepo.findByMemberEntityMemberNoAndCategoryNo(memberNo, categoryNo);
		for (BoardEntity board : boardList) {
	        int boardNo = board.getBoardNo();
	        log.info("회원별 문의글 번호: {}", boardNo);
	    }
	}
	
	@Test
	public void testRead2() throws Exception{
		int farmNo=1;
		int categoryNo=1;
		
		List<BoardEntity> boardListByFarm=boardrepo.findByFarmEntityFarmNoAndCategoryNo(farmNo, categoryNo);
		for (BoardEntity board : boardListByFarm) {
	        int boardNo = board.getBoardNo();
	        log.info("농장별 문의글 번호: {}", board);
	    }
	}
	
	
}

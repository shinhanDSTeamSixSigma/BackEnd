package site.greenwave.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import site.greenwave.board.entity.ReviewEntity;
import site.greenwave.board.repository.ReviewRepository;
import site.greenwave.farm.entity.FarmEntity;
import site.greenwave.member.entity.MemberEntity;

@SpringBootTest
@Slf4j
@Log4j2
@Commit
public class ReviewRepoTest {
	@Autowired
	private ReviewRepository reviewrepo;
	
	@Test
	public void testInsert() throws Exception{
		log.info("---------");
		MemberEntity memberEntity = new MemberEntity(); 
	    memberEntity.setMemberNo(1);
	    FarmEntity farmEntity = new FarmEntity();
	    farmEntity.setFarmNo(50);    
        for (int i = 1; i < 11; i++) {
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .reviewNo(i)
            		.reviewContent("리뷰내용 장고심 농부가 짱고심~!~!~")       
            		.rating(4)
            		.isDeleted(false)
            		.memberEntity(memberEntity)
            		.farmEntity(farmEntity)
            		.build();
            reviewrepo.save(reviewEntity);
        }
	}
}

package site.greenwave.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.greenwave.board.dto.ReviewDTO;
import site.greenwave.board.entity.ReviewEntity;
import site.greenwave.board.repository.ReviewRepository;
import site.greenwave.farm.entity.FarmEntity;
import site.greenwave.member.entity.MemberEntity;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewDTO registReview(ReviewDTO reviewDTO) {
        
    	 MemberEntity memberEntity = new MemberEntity();
         memberEntity.setMemberNo(reviewDTO.getMemberNo());
         
         FarmEntity farmEntity = new FarmEntity();
         farmEntity.setFarmNo(reviewDTO.getFarmNo());
    	
    	// DTO를 Entity로 변환
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .reviewContent(reviewDTO.getReviewContent())
                .rating(reviewDTO.getRating())
                .isDeleted(reviewDTO.isDeleted())     
                .memberEntity(memberEntity)
                .farmEntity(farmEntity)
                .build();
        
        // Entity를 저장
        ReviewEntity savedReviewEntity = reviewRepository.save(reviewEntity);

        // 저장된 Entity를 DTO로 변환하여 반환
        return ReviewDTO.builder()                
                .reviewContent(savedReviewEntity.getReviewContent())
                .rating(savedReviewEntity.getRating())
                .isDeleted(savedReviewEntity.isDeleted())
                .memberNo(savedReviewEntity.getMemberEntity().getMemberNo())
                .farmNo(savedReviewEntity.getFarmEntity().getFarmNo())
                .build();
    }
}
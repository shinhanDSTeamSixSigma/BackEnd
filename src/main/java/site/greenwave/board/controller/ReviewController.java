package site.greenwave.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import site.greenwave.board.dto.BoardDTO;
import site.greenwave.board.dto.ReviewDTO;
import site.greenwave.board.entity.BoardEntity;
import site.greenwave.board.entity.ReviewEntity;
import site.greenwave.board.repository.CommentRepository;
import site.greenwave.board.repository.ReviewRepository;
import site.greenwave.board.service.ReviewService;
import site.greenwave.member.repository.MemberRepository;

@CrossOrigin(origins= {"http://localhost:3000/","http://192.168.0.51:3000/"})
@RestController
@RequestMapping("/review")
@Slf4j
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private ReviewService reviewService;
	
	//농장별 리뷰 목록
	@GetMapping("/list")
    public List<ReviewDTO> getReviewListByFarmNo(@RequestParam int farmNo){
        List<ReviewEntity> reviewList = reviewRepo.findByFarmEntityFarmNoOrderByCreatedDateDesc(farmNo);
        return reviewList.stream()
                .map(review -> ReviewDTO.builder()
                        .reviewNo(review.getReviewNo())
                        .reviewContent(review.getReviewContent())
                        .createdDate(review.getCreatedDate())
                        .rating(review.getRating())
                        .isDeleted(review.isDeleted())
                        .memberNo(review.getMemberEntity().getMemberNo())
                        .nickname(review.getMemberEntity().getNickname())
                        .farmNo(review.getFarmEntity().getFarmNo()) 
                        .build())
                .collect(Collectors.toList());
	}
	
	//회원별 리뷰 목록
	@GetMapping("/{memberNo}/list")
    public List<ReviewDTO> getReviewListByMemberNo(@PathVariable int memberNo){
        List<ReviewEntity> reviewList = reviewRepo.findByMemberEntityMemberNoOrderByCreatedDateDesc(memberNo);
        return reviewList.stream()
                .map(review -> ReviewDTO.builder()
                        .reviewNo(review.getReviewNo())
                        .reviewContent(review.getReviewContent())
                        .createdDate(review.getCreatedDate())
                        .rating(review.getRating())
                        .isDeleted(review.isDeleted())
                        .memberNo(review.getMemberEntity().getMemberNo())
                        .memberId(review.getMemberEntity().getMemberId())
                        .farmNo(review.getFarmEntity().getFarmNo())
                        .farmId(review.getFarmEntity().getMemberEntity().getMemberId())
                        .build())
                .collect(Collectors.toList());
	}
	
	//리뷰 상세
    @GetMapping("/{reviewNo}")
    public ReviewDTO getReviewDetail(@PathVariable int reviewNo) {
        // 해당 reviewNo에 해당하는 상세내역을 데이터베이스에서 가져와서 ReviewDTO로 변환하여 반환
        ReviewEntity reviewEntity = reviewRepo.findById(reviewNo).orElse(null);
        if (reviewEntity == null) {
            // 요청한 번호에 해당하는 리뷰 없는 경우 처리
            throw new RuntimeException("Requested review not found with boardNo: " + reviewNo);
        }
       
        
        return ReviewDTO.builder()
                .reviewNo(reviewEntity.getReviewNo())
                .rating(reviewEntity.getRating())
                .reviewContent(reviewEntity.getReviewContent())
                .createdDate(reviewEntity.getCreatedDate())
                .isDeleted(reviewEntity.isDeleted())
                .memberNo(reviewEntity.getMemberEntity().getMemberNo())
                .nickname(reviewEntity.getMemberEntity().getNickname())
                .farmNo(reviewEntity.getFarmEntity().getFarmNo())
                .farmId(reviewEntity.getFarmEntity().getMemberEntity().getMemberId())
                .build();
    }
    //리뷰 수정
    @PutMapping("/edit/{reviewNo}")
    public ReviewDTO editReview(@RequestBody ReviewDTO reveiwDTO,@PathVariable int reviewNo) {
    	log.info("Editing review: {}", reveiwDTO);
        
        // 문의글을 식별하는 boardNo를 확인하고 해당하는 엔티티를 가져옴
        ReviewEntity reviewEntity = reviewRepo.findById(reviewNo)
                .orElseThrow(() -> new RuntimeException("Review not found with reviewNo: " + reviewNo));
        
        // 엔티티의 필드를 수정
        reviewEntity.setRating(reveiwDTO.getRating());
        reviewEntity.setReviewContent(reveiwDTO.getReviewContent());
        
        // 수정된 엔티티를 저장
       ReviewEntity updatedReviewEntity = reviewRepo.save(reviewEntity);
        
        // 수정된 엔티티를 DTO로 변환하여 반환
        return ReviewDTO.builder()
        		.reviewNo(updatedReviewEntity.getReviewNo())
                .rating(updatedReviewEntity.getRating())
                .reviewContent(updatedReviewEntity.getReviewContent())
                .createdDate(updatedReviewEntity.getCreatedDate())
                .isDeleted(updatedReviewEntity.isDeleted())
                .memberNo(updatedReviewEntity.getMemberEntity().getMemberNo())
                .nickname(updatedReviewEntity.getMemberEntity().getNickname())           
                .build();
    }
    
	//리뷰 등록
	@PostMapping("/regist")
	public ReviewDTO registReview(@RequestBody ReviewDTO reviewDTO) {
		log.info("[Data]: "+reviewDTO.toString());
		return reviewService.registReview(reviewDTO);
	}
	
	//리뷰 삭제
    @Transactional
    @DeleteMapping("/delete/{reviewNo}")
    public Map<String, Object> deleteInquiry(@PathVariable int reviewNo) {
    	log.info("Deleting review with reviewNo: {}", reviewNo);
    	
    	ReviewEntity reviewEntity = reviewRepo.findById(reviewNo)
                .orElseThrow(() -> new RuntimeException("Review not found with reviewNo: " + reviewNo));
    
    	reviewRepo.delete(reviewEntity);
    	Map<String, Object> result = new HashMap<>();
    	result.put("result", "success");
    	return result; 
    }
	
}

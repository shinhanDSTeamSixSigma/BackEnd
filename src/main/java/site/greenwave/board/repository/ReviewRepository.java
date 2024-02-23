package site.greenwave.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.board.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer>{
	//농장별 리뷰목록
	List<ReviewEntity> findByFarmEntityFarmNoOrderByCreatedDateDesc(int farmNo);
	
	//회원별 리뷰목록
	List<ReviewEntity> findByMemberEntityMemberNoOrderByCreatedDateDesc(int farmNo);

}

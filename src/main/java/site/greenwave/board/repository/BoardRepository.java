package site.greenwave.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	//회원별 문의글 목록
	List<BoardEntity> findByMemberEntityMemberNoAndCategoryNoOrderByCreatedDateDesc(int memberNo, int categoryNo);
	
	//농장별 문의글 목록
	List<BoardEntity> findByFarmEntityFarmNoAndCategoryNoOrderByCreatedDateDesc(int farmNo, int categoryNo);
	
	// 농장별 문의글 페이징 처리
	Page<BoardEntity> findByFarmEntityFarmNoAndCategoryNoOrderByCreatedDateDesc(int farmNo, int categoryNo, Pageable pageable);}

package site.greenwave.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	//회원별 문의글 목록
	List<BoardEntity> findByMemberEntityMemberNoAndCategoryNo(int memberNo, int categoryNo);
	
	//농장별 문의글 목록
	List<BoardEntity> findByFarmEntityFarmNoAndCategoryNo(int farmNo, int categoryNo);
	
}
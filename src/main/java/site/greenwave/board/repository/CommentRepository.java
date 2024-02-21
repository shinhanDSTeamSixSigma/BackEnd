package site.greenwave.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.board.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
	
	List<CommentEntity> findByPostNo(int postNo);
}

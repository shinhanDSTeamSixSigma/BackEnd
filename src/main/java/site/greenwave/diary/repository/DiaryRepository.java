package site.greenwave.diary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.diary.entity.DiaryEntity;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer>{
	List<DiaryEntity> findByMemberEntityMemberNoAndCropEntityCropNo(Integer memberNo, Integer cropNo);
}
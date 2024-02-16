package site.greenwave.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.diary.entity.DiaryEntity;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer>{

}
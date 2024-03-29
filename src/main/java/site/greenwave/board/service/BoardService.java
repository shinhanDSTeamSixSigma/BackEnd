package site.greenwave.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import site.greenwave.board.dto.BoardDTO;
import site.greenwave.board.dto.BoardPageRequestDTO;
import site.greenwave.board.entity.BoardEntity;
import site.greenwave.board.repository.BoardRepository;
import site.greenwave.farm.dto.PageResponseDto;
import site.greenwave.farm.entity.FarmEntity;
import site.greenwave.member.entity.MemberEntity;
import site.greenwave.member.repository.MemberRepository;

@Service
public class BoardService {

	private final BoardRepository boardRepo;
    private final MemberRepository memberRepo;

    @Autowired
    public BoardService(BoardRepository boardRepo, MemberRepository memberRepo) {
        this.boardRepo = boardRepo;
        this.memberRepo = memberRepo;
    }
    public BoardDTO registInquiry(BoardDTO boardDTO) {
    	// memberNo를 이용하여 MemberEntity 조회
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberNo(boardDTO.getMemberNo());
        
        FarmEntity farmEntity = new FarmEntity();
        farmEntity.setFarmNo(boardDTO.getFarmNo());

        // DTO를 엔티티로 변환
        BoardEntity boardEntity = BoardEntity.builder()
                .categoryNo(boardDTO.getCategoryNo())
                .title(boardDTO.getTitle())
                .boardContent(boardDTO.getBoardContent())
                .createdDate(boardDTO.getCreatedDate())
                .views(boardDTO.getViews())
                .isReplied(boardDTO.isReplied())
                .isDeleted(boardDTO.isDeleted())      
                .memberEntity(memberEntity)
                .farmEntity(farmEntity)
                .build();

        // 엔티티를 저장
        BoardEntity savedEntity = boardRepo.save(boardEntity);

        // 저장된 엔티티를 DTO로 변환하여 반환
        return BoardDTO.builder()
                .categoryNo(savedEntity.getCategoryNo())
                .title(savedEntity.getTitle())
                .boardContent(savedEntity.getBoardContent())
                .createdDate(savedEntity.getCreatedDate())
                .views(savedEntity.getViews())
                .isReplied(savedEntity.isReplied())
                .isDeleted(savedEntity.isDeleted())
                .memberNo(savedEntity.getMemberEntity().getMemberNo())
                .farmNo(savedEntity.getFarmEntity().getFarmNo())
                .build();
    }
    
// // 페이징 리스트
//    public PageResponseDto<BoardDTO> list(BoardPageRequestDTO pageRequestDto) {
//        int farmNo = pageRequestDto.getFarmNo();
//        int categoryNo = pageRequestDto.getCategoryNo();
//
//        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1,
//                pageRequestDto.getSize(),
//                Sort.by("boardNo").descending());
//
//        Page<BoardEntity> result = boardRepo.findByFarmEntityFarmNoAndCategoryNoOrderByCreatedDateDesc(farmNo, categoryNo, pageable);
//        
//        // BoardEntity를 BoardDTO로 변환
//        List<BoardDTO> dtoList = result.getContent().stream()
//                .map(boardEntity -> modelMapper.map(boardEntity, BoardDTO.class))
//                .collect(Collectors.toList());
//
//        long totalCount = result.getTotalElements();
//
//        return PageResponseDto.<BoardDTO>withAll()
//                .dtoList(dtoList)
//                .pageRequestDto(pageRequestDto)
//                .totalCount(totalCount)
//                .build();
//    }
}


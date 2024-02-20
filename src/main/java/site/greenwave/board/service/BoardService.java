package site.greenwave.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.greenwave.board.dto.BoardDTO;
import site.greenwave.board.entity.BoardEntity;
import site.greenwave.board.repository.BoardRepository;
import site.greenwave.farm.FarmEntity;
import site.greenwave.member.MemberEntity;
import site.greenwave.member.MemberRepository;

@Service
public class BoardService {

    @Autowired
    private static BoardRepository boardRepo;
    @Autowired
    private static MemberRepository memberRepo;
    public static BoardDTO registInquiry(BoardDTO boardDTO) {
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
                .boardNo(savedEntity.getBoardNo())
                .categoryNo(savedEntity.getCategoryNo())
                .title(savedEntity.getTitle())
                .boardContent(savedEntity.getBoardContent())
                .createdDate(savedEntity.getCreatedDate())
                .views(savedEntity.getViews())
                .isReplied(savedEntity.isReplied())
                .isDeleted(savedEntity.isDeleted())
                .memberNo(savedEntity.getMemberEntity().getMemberNo())
                .memberId(savedEntity.getMemberEntity().getMemberId())
                .farmNo(savedEntity.getFarmEntity().getFarmNo())
                .build();
    }
}
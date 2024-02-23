package site.greenwave.board.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.greenwave.board.dto.BoardDTO;
import site.greenwave.board.entity.BoardEntity;
import site.greenwave.board.repository.BoardRepository;
import site.greenwave.board.service.BoardService;

@CrossOrigin(origins= {"http://localhost:3000/","http://192.168.0.51:3000/"})
@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {
	@Autowired
	private BoardRepository boardRepo;
	
	//농장별 문의글 목록
    @GetMapping("/inquiryList")
    public List<BoardDTO> getBoardListByFarmNo(@RequestParam int farmNo, @RequestParam int categoryNo){
        List<BoardEntity> boardList = boardRepo.findByFarmEntityFarmNoAndCategoryNo(farmNo, categoryNo);
        return boardList.stream()
                .map(board -> BoardDTO.builder()
                        .boardNo(board.getBoardNo())
                        .categoryNo(board.getCategoryNo())
                        .title(board.getTitle())
                        .boardContent(board.getBoardContent())
                        .createdDate(board.getCreatedDate())
                        .views(board.getViews())
                        .isReplied(board.isReplied())
                        .isDeleted(board.isDeleted())
                        .memberNo(board.getMemberEntity().getMemberNo())
                        .memberId(board.getMemberEntity().getMemberId())
                        .farmNo(board.getFarmEntity().getFarmNo()) 
                        .build())
                .collect(Collectors.toList());
    }
    
    //농장 문의 등록
    @PostMapping("/inquiryRegist")
    public BoardDTO registInquiry(@RequestBody BoardDTO boardDTO) {
    	log.info("[Data]: "+boardDTO.toString());
    	return BoardService.registInquiry(boardDTO);
    }
    
    //문의글 상세
    @GetMapping("/detail/{boardNo}")
    public BoardDTO getBoardDetail(@PathVariable int boardNo) {
        // 해당 boardNo에 해당하는 문의글을 데이터베이스에서 가져와서 BoardDTO로 변환하여 반환
        BoardEntity boardEntity = boardRepo.findById(boardNo).orElse(null);
        if (boardEntity == null) {
            // 요청한 번호에 해당하는 문의글이 없는 경우 처리
            throw new RuntimeException("Requested board not found with boardNo: " + boardNo);
        }

        return BoardDTO.builder()
                .boardNo(boardEntity.getBoardNo())
                .categoryNo(boardEntity.getCategoryNo())
                .title(boardEntity.getTitle())
                .boardContent(boardEntity.getBoardContent())
                .createdDate(boardEntity.getCreatedDate())
                .views(boardEntity.getViews())
                .isReplied(boardEntity.isReplied())
                .isDeleted(boardEntity.isDeleted())
                .memberNo(boardEntity.getMemberEntity().getMemberNo())
                .memberId(boardEntity.getMemberEntity().getMemberId())
                .farmNo(boardEntity.getFarmEntity().getFarmNo())
                .build();
    }
    
}

package site.greenwave.board.controller;

import java.util.ArrayList;
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
import site.greenwave.board.dto.CommentDTO;
import site.greenwave.board.entity.BoardEntity;
import site.greenwave.board.entity.CommentEntity;
import site.greenwave.board.repository.BoardRepository;
import site.greenwave.board.repository.CommentRepository;
import site.greenwave.board.service.BoardService;
import site.greenwave.member.entity.MemberEntity;
import site.greenwave.member.repository.MemberRepository;

@CrossOrigin(origins= {"http://localhost:3000/","http://192.168.0.51:3000/"})
@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardService boardService;
	
	//농장별 문의글 목록
    @GetMapping("/inquiryList")
    public List<BoardDTO> getBoardListByFarmNo(@RequestParam int farmNo, @RequestParam int categoryNo){
        List<BoardEntity> boardList = boardRepo.findByFarmEntityFarmNoAndCategoryNoOrderByCreatedDateDesc(farmNo, categoryNo);
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
                        .nickname(board.getMemberEntity().getNickname())
                        .farmNo(board.getFarmEntity().getFarmNo()) 
                        .build())
                .collect(Collectors.toList());
    }
//    //문의글 페이징
//    @GetMapping("/list") 
//    public ResponseEntity<PageResponseDto<BoardDTO>> getPaging(BoardPageRequestDTO pageRequestDto) {
//        PageResponseDto<BoardDTO> list = boardService.list(pageRequestDto);
//        return ResponseEntity.status(HttpStatus.OK).body(list);
//    }
    
    //회원별 문의글 목록
    @GetMapping("/{memberNo}/inquiryList")
    public List<BoardDTO> getBoardListByMemberNo(@PathVariable int memberNo, @RequestParam int categoryNo){
        List<BoardEntity> boardList = boardRepo.findByMemberEntityMemberNoAndCategoryNoOrderByCreatedDateDesc(memberNo, categoryNo);
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
                        .nickname(board.getMemberEntity().getNickname())
                        .farmNo(board.getFarmEntity().getFarmNo()) 
                        .build())
                .collect(Collectors.toList());
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
        boardEntity.setViews(boardEntity.getViews()+1);
        boardRepo.save(boardEntity);
        
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
                .nickname(boardEntity.getMemberEntity().getNickname())
                .farmNo(boardEntity.getFarmEntity().getFarmNo())
                .build();
    }
    
    //문의 등록
    @PostMapping("/inquiryRegist")
    public BoardDTO registInquiry(@RequestBody BoardDTO boardDTO) {
    	log.info("[Data]: "+boardDTO.toString());
    	return boardService.registInquiry(boardDTO);
    }
    
    //문의 수정
    @PutMapping("/inquiryEdit/{boardNo}")
    public BoardDTO editInquiry(@RequestBody BoardDTO boardDTO,@PathVariable int boardNo) {
    	log.info("Editing inquiry: {}", boardDTO);
        
        // 문의글을 식별하는 boardNo를 확인하고 해당하는 엔티티를 가져옴
        BoardEntity boardEntity = boardRepo.findById(boardNo)
                .orElseThrow(() -> new RuntimeException("Board not found with boardNo: " + boardNo));
        
        // 엔티티의 필드를 수정
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
        
        // 수정된 엔티티를 저장
        BoardEntity updatedBoardEntity = boardRepo.save(boardEntity);
        
        // 수정된 엔티티를 DTO로 변환하여 반환
        return BoardDTO.builder()
                .boardNo(updatedBoardEntity.getBoardNo())
                .categoryNo(updatedBoardEntity.getCategoryNo())
                .title(updatedBoardEntity.getTitle())
                .boardContent(updatedBoardEntity.getBoardContent())
                .createdDate(updatedBoardEntity.getCreatedDate())
                .views(updatedBoardEntity.getViews())
                .isReplied(updatedBoardEntity.isReplied())
                .isDeleted(updatedBoardEntity.isDeleted())
                .memberNo(updatedBoardEntity.getMemberEntity().getMemberNo())
                .nickname(updatedBoardEntity.getMemberEntity().getNickname())
                .memberId(updatedBoardEntity.getMemberEntity().getMemberId())
                .farmNo(updatedBoardEntity.getFarmEntity().getFarmNo())
                .build();
    }
    
    //문의 삭제
    @Transactional
    @DeleteMapping("/inquiryDelete/{boardNo}")
    public Map<String, Object> deleteInquiry(@PathVariable int boardNo) {
    	log.info("Deleting inquiry with boardNo: {}", boardNo);
    	
    	BoardEntity boardEntity = boardRepo.findById(boardNo)
                .orElseThrow(() -> new RuntimeException("Board not found with boardNo: " + boardNo));
    
    	boardRepo.delete(boardEntity);
    	Map<String, Object> result = new HashMap<>();
    	result.put("result", "success");
    	return result; 
    }
    
    //답변 목록
    @GetMapping("/{boardNo}/commentlist")
    public List<CommentDTO> getCommentListByBoardNo(@PathVariable int boardNo){
    	 // boardNo에 해당하는 답변 목록을 가져옴
        List<CommentEntity> commentList = commentRepo.findByPostNo(boardNo);
        
        // CommentEntity를 CommentDTO로 변환하는 작업
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentList) {
            CommentDTO commentDTO = new CommentDTO();
            // CommentEntity에서 필요한 정보를 가져와 CommentDTO에 설정
            commentDTO.setCommentContent(commentEntity.getCommentContent());
            commentDTO.setCommentDate(commentEntity.getCommentDate());
            commentDTO.setNickname(commentEntity.getMemberEntity().getNickname());            
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
    
    //답변 등록
    @PostMapping("/{boardNo}/comment")
    public Map<String, Object> addComment(@PathVariable int boardNo, @RequestBody Map<String, Object> requestBody) {
        log.info("Adding comment to boardNo: {}", boardNo);
        
        // 요청 본문에서 content, memberNo, commentDate를 가져옴
        String content = (String) requestBody.get("content");
        int memberNo = (int) requestBody.get("memberNo");
        String commentDate = (String) requestBody.get("commentDate");
        
        // boardNo를 이용하여 해당 게시물 정보를 가져옴
        BoardEntity boardEntity = boardRepo.findById(boardNo)
                .orElseThrow(() -> new RuntimeException("Board not found with boardNo: " + boardNo));
        // memberNo를 이용하여 MemberEntity 가져옴
        MemberEntity memberEntity = memberRepo.findById(memberNo)
                .orElseThrow(() -> new RuntimeException("Member not found with memberNo: " + memberNo));

        // CommentEntity 객체 생성 및 설정
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentContent(content);
        commentEntity.setPostNo(boardNo);
        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setCommentCate(boardEntity.getCategoryNo());
        
        // CommentEntity 저장
        commentEntity = commentRepo.save(commentEntity);
        
        // 문의글에 답변이 등록되었으므로 isReplied 필드를 1로 설정
        boardEntity.setReplied(true);
        boardRepo.save(boardEntity); // 변경 사항 저장
        
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return result; 
    }
    
}

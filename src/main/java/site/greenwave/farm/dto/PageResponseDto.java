package site.greenwave.farm.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDto<E> {

    // 현재 페이지에 표시될 dto list
    private List<E> dtoList;
    // 페이지네이션의 페이지 번호 목록
    private List<Integer> pageNumList;
    // 페이지 요청정보 담은 DTO 객체(페이지 번호와, 페이지당 항목 수)
    private PageRequestDto pageRequestDto;
    // 이전 페이지와 다음 페이지 존재 여부
    private boolean prev, next;
    // 전체 항목 수, 이전,다음 페이지 번호, 현재 페이지 번호, 전체 페이지 수,
    private int totalCount, prevPage, nextPage, current, totalPage;

    // 정적 팩토리 메서드 - 클래스 인스턴스를 생성하고 반환하는 정적 메서드
    @Builder(builderMethodName = "withAll")
    public PageResponseDto(List<E> dtoList, PageRequestDto pageRequestDto, long totalCount) {
        this.dtoList = dtoList;
        this.pageRequestDto = pageRequestDto;
        this.totalCount = (int) totalCount;

        // 페이지네이션의 페이지 번호 목록, ex) 전체 항목 수 47, 페이지당 항목이 10이라면 5페이지 존재하면 페이지네이션 [1,2,3,4,5]
        int lastPageNum = (int) Math.ceil((double) totalCount / pageRequestDto.getSize());
        // 이전 페이지 여부 , ex) 페이지 시작 번호는 1부터니까 현재 페이지 시작 번호가 1보다 크면 1페이지 이상이므로 이전 페이지 존재
        this.prev = pageRequestDto.getPage() > 1;
        // 다음 페이지 여부, ex) 현재 페이지 마지막 번호 10, 토탈 항목수가 50이면 다음 페이지 존재
        this.next = pageRequestDto.getPage() < lastPageNum;

        // 페이지네이션의 페이지 번호 목록 생성, ex) 1 <= range <= lastPageNum, boxed는 Stream<Inteager>로 변환, collect로 stream을 list로 변환
        this.pageNumList = IntStream.rangeClosed(1, lastPageNum).boxed().collect(Collectors.toList());


        // 이전 페이지와 다음 페이지 번호 계산
        if (prev) {
            this.prevPage = pageRequestDto.getPage() - 1;
        }
        if (next) {
            this.nextPage = pageRequestDto.getPage() + 1;
        }

        // 현재 페이지와 전체 페이지 수 설정
        this.current = pageRequestDto.getPage();
        this.totalPage = lastPageNum;
    }
}

package site.greenwave.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import site.greenwave.member.entity.MemberEntity;
import site.greenwave.member.repository.MemberRepository;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void regist() throws Exception {
        for (int i = 1; i < 30; i++) {
            MemberEntity member = MemberEntity.builder()
                    .memberId("a" + (i + 1))
                    .memberPwd("1111"+i)
                    .memberName("kky" + i)
                    .phone("010111111" + i)
                    .nickname("토뭉" + i)
                    .address1("경기 수원" + i)
                    .address2("서울특별시" + i)
                    .memberPoint(1000 + i)
                    .zipcode("ads" + i)
                    .build();
            memberRepository.save(member);
        }
    }
}
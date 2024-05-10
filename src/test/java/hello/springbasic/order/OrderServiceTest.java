package hello.springbasic.order;

import hello.springbasic.AppConfig;
import hello.springbasic.member.Grade;
import hello.springbasic.member.Member;
import hello.springbasic.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    @Test
    public void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "쿨쿨", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "연어덮밥", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

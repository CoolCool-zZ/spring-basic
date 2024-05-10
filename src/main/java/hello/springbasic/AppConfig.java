package hello.springbasic;

import hello.springbasic.discount.RateDiscountPolicy;
import hello.springbasic.member.MemberService;
import hello.springbasic.member.MemberServiceImpl;
import hello.springbasic.member.MemoryMemberRepository;
import hello.springbasic.order.OrderService;
import hello.springbasic.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }
}

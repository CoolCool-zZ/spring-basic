package hello.springbasic;

import hello.springbasic.discount.DiscountPolicy;
import hello.springbasic.discount.RateDiscountPolicy;
import hello.springbasic.member.MemberRepository;
import hello.springbasic.member.MemberService;
import hello.springbasic.member.MemberServiceImpl;
import hello.springbasic.member.MemoryMemberRepository;
import hello.springbasic.order.OrderService;
import hello.springbasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}

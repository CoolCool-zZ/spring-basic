package hello.springbasic.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.springbasic.AppConfig;
import hello.springbasic.discount.DiscountPolicy;
import hello.springbasic.discount.FixDiscountPolicy;
import hello.springbasic.discount.RateDiscountPolicy;
import hello.springbasic.member.MemberService;
import hello.springbasic.member.MemberServiceImpl;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextFindBeanTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    ApplicationContext acWithSameBean =
            new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Configuration
    static class SameBeanConfig {
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
    }

    @Test
    @DisplayName("빈 이름과 타입으로 조회")
    public void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    public void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름과 구현체 타입으로 조회")
    public void findBeanByImplementationType() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름과 타입으로 조회 실패")
    public void findBeanByNameFail() {
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }

    @Test
    @DisplayName("같은 타입의 빈이 2개 이상인 경우, 타입으로만 조회 시 중복 오류 발생")
    public void findBeanByTypeDuplicateFail() {
        assertThrows(
                NoUniqueBeanDefinitionException.class,
                () -> acWithSameBean.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("같은 타입의 빈이 2개 이상인 경우, 이름과 타입으로 조회 시 성공")
    public void findBeanByTypeDuplicateSuccess() {
        DiscountPolicy discountPolicy =
                acWithSameBean.getBean("fixDiscountPolicy", DiscountPolicy.class);
        assertThat(discountPolicy).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("같은 타입의 빈이 2개 이상인 경우, 해당 타입의 모든 빈 조회 시 성공")
    public void findAllDuplicatedTypeBean() {
        Map<String, DiscountPolicy> discountPolicyBeans =
                acWithSameBean.getBeansOfType(DiscountPolicy.class);

        System.out.println("discountPolicyBeans : " + discountPolicyBeans);

        for (String key : discountPolicyBeans.keySet()) {
            System.out.println("key=" + key + " value=" + discountPolicyBeans.get(key));
        }
        assertThat(discountPolicyBeans.size()).isEqualTo(2);
    }
}

package hello.springbasic.discount;

import hello.springbasic.member.Grade;
import hello.springbasic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void discountWhenVip() {
        // given
        Member member = new Member(1L, "쿨쿨VIP", Grade.VIP);

        // then
        int discount = discountPolicy.discount(member, 10000);

        // when
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void discountWhenNotVip() {
        // given
        Member member = new Member(1L, "쿨쿨VIP", Grade.BASIC);

        // then
        int discount = discountPolicy.discount(member, 10000);

        // when
        Assertions.assertThat(discount).isEqualTo(0);
    }
}

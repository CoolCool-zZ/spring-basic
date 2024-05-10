package hello.springbasic.bean;

import hello.springbasic.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext acWithXml = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 자바코드 설정 메타정보 확인")
    void findApplicationBeanWithJavaAc() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinition" + beanDefinition);
            }
        }
    }

    @Test
    @DisplayName("빈 xml 설정 메타정보 확인")
    void findApplicationBeanWithXmlAc() {
        String[] beanDefinitionNames = acWithXml.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = acWithXml.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinition" + beanDefinition);
            }
        }
    }
}

package jpa.shop.component;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HibernateModule {

    /**
     * 지연로딩 형태
     * 세팅하지 않은 상태에서 지연로딩은 null로 표시하게 만들어준다
     * <p>
     * force_lazy_loading일 경우 데이터를 긁어오는 형태
     *
     * @return
     */
    @Bean
    private Hibernate5Module hibernate5Module() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        return hibernate5Module;
    }
}

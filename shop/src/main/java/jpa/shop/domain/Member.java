package jpa.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotNull
    private String name;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY
            , mappedBy = "member")
    private List<Order> orders = new ArrayList();
}

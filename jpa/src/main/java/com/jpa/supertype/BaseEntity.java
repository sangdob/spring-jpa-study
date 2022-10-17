package com.jpa.supertype;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    private String createBy;
    private LocalDateTime createDate;
    private String updateBy;
    private LocalDateTime updateDate;
}

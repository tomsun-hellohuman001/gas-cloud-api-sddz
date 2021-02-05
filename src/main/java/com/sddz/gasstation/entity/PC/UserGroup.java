package com.sddz.gasstation.entity.PC;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * <Description> 用户组 <br>
 *
 * @author zhaopengwei<br>
 */
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_group")
@Entity
@Getter
@Setter
public class UserGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer count;

    private Long stationId;

    private Long oilCompanyId;

    @LastModifiedDate
    private LocalDateTime modifyTime;

    @LastModifiedBy
    private Long modifier;
}

package com.sddz.gasstation.entity.PC;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * <Description> 用户组用户映射 <br>
 *
 * @author zhaopengwei<br>
 */
@Table(name = "user_group_user_relation")
@Entity
@Getter
@Setter
public class UserGroupUserRelation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_group_id")
    private Long userGroupId;

    private String userGroupName;

    private Long userId;

    private String nickName;

    private String phone;
}

package com.sddz.gasstation.dao;

import com.sddz.gasstation.entity.PC.UserGroupUserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * <Description> 用户组用户映射 <br>
 *
 * @author zhaopengwei<br>
 */
public interface UserGroupUserRelationRepository extends JpaRepository<UserGroupUserRelation, Long>, JpaSpecificationExecutor<UserGroupUserRelation> {

    List<UserGroupUserRelation> findByUserGroupId(Long userGroupId);

    List<UserGroupUserRelation> findByPhone(String phone);

    Optional<UserGroupUserRelation> findByUserGroupIdAndPhone(Long userGroupId, String phone);
}

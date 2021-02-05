package com.sddz.gasstation.dao;

import com.sddz.gasstation.entity.PC.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * <Description> 用户组 <br>
 *
 * @author zhaopengwei<br>
 */
public interface UserGroupRepository extends JpaRepository<UserGroup, Long>, JpaSpecificationExecutor<UserGroup> {

    List<UserGroup> findByStationId(Long stationId);
}

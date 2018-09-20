package com.dao.mapper;

import com.dao.model.AdminPrivilege;
import com.dao.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author music
 */
@Repository
public interface AdminPrivilegeMapper extends MyMapper<AdminPrivilege> {


    /**
     * 根据用户ID 查询用户权限
     *
     * @param adminId
     * @return
     */
    List<String> getAdminCompetenceRes(Long adminId);

}
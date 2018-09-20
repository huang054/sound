package com.dao.mapper;

import com.dao.model.AdminGr;
import com.dao.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author music
 */
@Repository
public interface AdminGrMapper extends MyMapper<AdminGr> {

    /**
     * 删除权限
     *
     * @param gId       组ID
     * @param deleteRec 权限资源
     * @return
     */
    Integer deleteRoleRec(@Param(value = "gId") Long gId, @Param(value = "deleteRec") List<Long> deleteRec);

    /**
     * 增加权限
     *
     * @param gId    组ID
     * @param addRec 权限资源
     * @return
     */
    Integer insertRoleRec(@Param(value = "gId") Long gId, @Param(value = "addRec") List<Long> addRec);
}
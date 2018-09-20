package com.dao.mapper;

import com.dao.model.Admin;
import com.dao.util.MyMapper;
import com.vo.AccountInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author music
 */
@Repository
public interface AdminMapper extends MyMapper<Admin> {


    /**
     * 插入用户 返回主键ID
     *
     * @param admin
     * @return
     */
    int insertAndGetId(Admin admin);

    /**
     * 查询用户信息
     *
     * @param userName
     * @return
     */
    Admin findUserInfo(@Param("userName") String userName);

    /**
     * 根据登录账户查询用户信息
     *
     * @param lowercaseLogin
     * @return
     */
    Admin selectAuthByUsername(@Param("lowercaseLogin") String lowercaseLogin);

    /**
     * 查询系统帐户
     *
     * @param adminId
     * @return
     */
    List<AccountInfoVo> findSystemUsers(@Param("adminId") Long adminId);

}
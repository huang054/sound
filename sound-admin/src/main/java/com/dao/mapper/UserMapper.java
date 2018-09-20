package com.dao.mapper;

import com.dao.model.User;
import com.dao.util.MyMapper;
import com.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends MyMapper<User> {

    /**
     * 查询用户列表
     *
     * @param userName    用户名
     * @param phoneNumber 手机号
     * @return
     */
    List<UserInfoVo> findUsers(@Param(value = "userName") String userName,
                               @Param(value = "phoneNumber") String phoneNumber);
}
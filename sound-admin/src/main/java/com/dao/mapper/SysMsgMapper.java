package com.dao.mapper;

import com.dao.model.SysMsg;
import com.dao.util.MyMapper;
import com.vo.SystemMsgInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author music
 */
@Repository
public interface SysMsgMapper extends MyMapper<SysMsg> {

    /**
     * 查询系统消息列表
     *
     * @return
     */
    List<SystemMsgInfoVo> findSystemMsgList();
}
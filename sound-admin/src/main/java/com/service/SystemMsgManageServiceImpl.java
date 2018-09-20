package com.service;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.SysMsgMapper;
import com.dao.model.SysMsg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.utils.util.TimeUtil;
import com.vo.SystemMsgInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author music
 */
@Service
public class SystemMsgManageServiceImpl implements SystemMsgManageService {


    @Autowired
    private SysMsgMapper sysMsgMapper;

    /**
     * 查询系统消息列表
     *
     * @param pageNum 当前页
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<SystemMsgInfoVo>> findSystemMsgList(Integer pageNum) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<SystemMsgInfoVo> systemMsgInfoVos = sysMsgMapper.findSystemMsgList();
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<SystemMsgInfoVo>) systemMsgInfoVos).toPageInfo());
    }

    /**
     * 查询系统消息信息
     *
     * @param systemMsgId 系统消息ID
     * @return
     */
    @Override
    public ResponseWrap<SystemMsgInfoVo> findSystemMsgInfo(Long systemMsgId) {
        if (systemMsgId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        SysMsg sysMsg = sysMsgMapper.selectByPrimaryKey(systemMsgId);
        if (sysMsg == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1102);
        }
        SystemMsgInfoVo systemMsgInfoVo = new SystemMsgInfoVo();
        systemMsgInfoVo.setSystemMsgId(sysMsg.getId());
        systemMsgInfoVo.setTitle(sysMsg.getTitle());
        systemMsgInfoVo.setSystemMsgContent(sysMsg.getContent());
        systemMsgInfoVo.setShowStatus(sysMsg.getShowStatus());
        systemMsgInfoVo.setReleaseTime(TimeUtil.getDateFromDateEngMMString(sysMsg.getCreateTime()));
        return new ResponseWrap<>(ResponseCode.CODE_200, systemMsgInfoVo);
    }

    /**
     * 更新系统消息信息
     *
     * @param systemMsgInfoVo
     * @return
     */
    @Override
    public ResponseWrap<Integer> updateSystemMsgInfo(SystemMsgInfoVo systemMsgInfoVo) {
        if (systemMsgInfoVo.getSystemMsgId() == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        SysMsg sysMsg = new SysMsg();
        sysMsg.setId(systemMsgInfoVo.getSystemMsgId());
        sysMsg.setTitle(systemMsgInfoVo.getTitle());
        sysMsg.setShowStatus(systemMsgInfoVo.getShowStatus());
        sysMsg.setContent(systemMsgInfoVo.getSystemMsgContent());
        return new ResponseWrap<>(ResponseCode.CODE_200, sysMsgMapper.updateByPrimaryKeySelective(sysMsg));
    }

    /**
     * 插入系统消息信息
     *
     * @param systemMsgInfoVo
     * @return
     */
    @Override
    public ResponseWrap<Integer> insertSystemMsgInfo(SystemMsgInfoVo systemMsgInfoVo) {
        SysMsg sysMsg = new SysMsg();
        sysMsg.setTitle(systemMsgInfoVo.getTitle());
        sysMsg.setShowStatus(systemMsgInfoVo.getShowStatus());
        sysMsg.setContent(systemMsgInfoVo.getSystemMsgContent());
        sysMsg.setCreateTime(new Date());
        sysMsg.setImgurl("");
        sysMsg.setImgName("");
        return new ResponseWrap<>(ResponseCode.CODE_200, sysMsgMapper.insert(sysMsg));
    }

    /**
     * 删除系统消息信息
     *
     * @param systemMsgId 系统消息ID
     * @return
     */
    @Override
    public ResponseWrap<Integer> deleteSystemMsgInfo(Long systemMsgId) {
        if (systemMsgId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        return new ResponseWrap<>(ResponseCode.CODE_200, sysMsgMapper.deleteByPrimaryKey(systemMsgId));
    }
}

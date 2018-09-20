package com.service;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.DeviceMapper;
import com.dao.mapper.UserMapper;
import com.dao.model.Device;
import com.dao.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.utils.util.TimeUtil;
import com.vo.UserDetails;
import com.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author music
 */
@Service
public class UserManageServiceImpl implements UserManageService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeviceMapper deviceMapper;

    /**
     * 查询用户列表
     *
     * @param userName    用户名
     * @param phoneNumber 手机号码
     * @param pageNum     当前页
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<UserInfoVo>> findUsers(String userName, String phoneNumber, Integer pageNum) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<UserInfoVo> userInfoVoList = userMapper.findUsers(userName, phoneNumber);
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<UserInfoVo>) userInfoVoList).toPageInfo());
    }


    /**
     * 查询用户详情
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public ResponseWrap<UserDetails> findUserInfo(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1102);
        }
        UserDetails userDetails = new UserDetails();
        userDetails.setBirthday(TimeUtil.getSimpleDateEngString(user.getBirthday()));
        userDetails.setCity(user.getAdress());
        userDetails.setPhoneNumber(user.getPhoneNum());
        userDetails.setSex(user.getSex());
        userDetails.setUserName(user.getName());
        userDetails.setReferrer(userMapper.selectByPrimaryKey(user.getInviteUserId()).getName());

        //查询绑定设备
        Example example = new Example(Device.class);
        Example.Criteria or = example.or();
        or.andEqualTo("userId", user.getId());
        List<Device> devices = deviceMapper.selectByExample(example);
        userDetails.setBindingDevice(devices.stream().map(x -> x.getDeviceName()).collect(Collectors.toList()));
        return new ResponseWrap<>(ResponseCode.CODE_200, userDetails);
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return
     */
    public ResponseWrap<Integer> deleteUser(Long userId) {
        return new ResponseWrap<>(ResponseCode.CODE_200, userMapper.deleteByPrimaryKey(userId));
    }
}

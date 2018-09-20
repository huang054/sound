package com.sound.service;

import com.sound.dao.UserDAO;
import com.sound.model.UserModel;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

 
/**
 * Created by Administrator on 2018/4/28.
 */ 
@Service
public class UserService extends BaseService<UserModel>{

    @Autowired
    private UserDAO userDAO;

    /**
     * 登陆
     * @return
     */

   /* public UserModel login(String userId,String password){
        UserModel user = userDAO.login(userId,password);

        return user;
    }
*/


    /**
     * 注册账号
     * @return
     */

    public UserModel save(UserModel model){

        return userDAO.save(model);
        
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */

    public UserModel findById(Long id){

        Optional<UserModel>  opt = userDAO.findById(id);

        if(opt != null){
            return opt.get();
        }
        return null;

    }

    public UserModel findByUserName(String userName){
        return userDAO.getByUserName(userName);
    }
    
    public Iterable<UserModel> findByDeviceId(String deviceId){
        return userDAO.findByDeviceId(deviceId);
    }
    
    public UserModel findByUserToken(String token){
    	return userDAO.getByUserToken(token);
    }
    
    public void updateTokenByPhone(String token,String phone) {
    	userDAO.updateTokenByPhone(token, phone);
    }
    
    public void updateSignIn() {
    	userDAO.updateSignIn();
    }
    
    
    public long findUserCout() {
    	return userDAO.getUserNum();
    }
    
    public long findUserCount(int big) {
    	return userDAO.findUserCount(big);
    }
    public int inviteUser(long id) {
    	return userDAO.inviteUser(id);
    }
    public void updateYestodaySignIn() {
    	userDAO.updateYestodaySignIn();
    }
}

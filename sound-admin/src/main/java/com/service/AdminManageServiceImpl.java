package com.service;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.*;
import com.dao.model.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vo.AccountAuthVO;
import com.vo.AccountInfoVo;
import com.vo.CompetenceRes;
import com.vo.RoleInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2018/5/5
 */
@Service
public class AdminManageServiceImpl implements AdminManageService {


    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminGroupMapper adminGroupMapper;
    @Autowired
    private AdminPrivilegeMapper adminPrivilegeMapper;
    @Autowired
    private AdminResMapper adminResMapper;
    @Autowired
    private AdminGrMapper adminGrMapper;

    /**
     * 添加管理员
     *
     * @param accountInfoVo 信息参数
     * @return
     */
    @Override
    @Transactional
    public ResponseWrap<Integer> addSysRole(AccountInfoVo accountInfoVo) {
        Admin admin = new Admin();
        //拼接前缀
        admin.setUserName(StaticConstant.SYS_LOGIN_PREFIX + accountInfoVo.getUserAccount());
        //判断该用户是否还存在
        Admin accountAuthVO = adminMapper.selectAuthByUsername(admin.getUserName());
        if (accountAuthVO != null && accountAuthVO.getId() != null) {
            return new ResponseWrap<>(ResponseCode.CODE_1008);
        }
        //查询角色是否存在
        AdminGroup adminGroup = adminGroupMapper.selectByPrimaryKey(accountInfoVo.getRoleId());
        if (adminGroup == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1102);
        }
        //密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encrptPassword = bCryptPasswordEncoder.encode(accountInfoVo.getPassword());
        admin.setUserName(admin.getUserName());
        admin.setPasswd(encrptPassword);
        admin.setContact(accountInfoVo.getContact());
        admin.setPhoneNumber(accountInfoVo.getPhoneNumber());
        admin.setCreateTime(new Date());
        //插入Account表
        int result = adminMapper.insertAndGetId(admin);
        if (result > 0) {
            //添加权限关联
            AdminPrivilege adminPrivilege = new AdminPrivilege();
            adminPrivilege.setAdminId(admin.getId());
            adminPrivilege.setGroupId(accountInfoVo.getRoleId());
            int insert = adminPrivilegeMapper.insert(adminPrivilege);
            if (insert > 0) {
                return new ResponseWrap<>(ResponseCode.CODE_200, insert);
            }
        }
        //手动事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return new ResponseWrap<>(ResponseCode.CODE_1103);
    }

    /**
     * 根据登录账户查询用户信息
     *
     * @param userName 用户账户
     * @return
     */
    @Override
    public AccountAuthVO selectAuthByUsername(String userName) {
        Admin admin = adminMapper.selectAuthByUsername(userName);
        AccountAuthVO accountAuthVO = new AccountAuthVO();
        if (admin == null) {
            return accountAuthVO;
        }
        accountAuthVO.setId(admin.getId());
        //加载资源文件
        accountAuthVO.setResourceName(adminPrivilegeMapper.getAdminCompetenceRes(admin.getId()));
        accountAuthVO.setUserPasswd(admin.getPasswd());
        accountAuthVO.setUserAccount(admin.getUserName());
        accountAuthVO.setStatus((byte) 1);
        return accountAuthVO;
    }

    /**
     * 查询系统帐户列表
     *
     * @param pageNum 当前页
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<AccountInfoVo>> findSystemUsers(Integer pageNum) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<AccountInfoVo> accountInfoVos = adminMapper.findSystemUsers(null);
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<AccountInfoVo>) accountInfoVos).toPageInfo());
    }

    /**
     * 查询系统帐户信息
     *
     * @param adminId 用户ID
     * @return
     */
    @Override
    public ResponseWrap<AccountInfoVo> findSystemUserInfo(Long adminId) {
        List<AccountInfoVo> accountInfoVos = adminMapper.findSystemUsers(adminId);
        if (accountInfoVos != null && accountInfoVos.size() != 1) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        return new ResponseWrap<>(ResponseCode.CODE_200, accountInfoVos.get(0));
    }

    /**
     * 删除系统帐户信息
     *
     * @param adminId 用户ID
     * @return
     */
    @Override
    @Transactional
    public ResponseWrap<Integer> deleteSystemUser(Long adminId) {
        int result = adminMapper.deleteByPrimaryKey(adminId);
        if (result > 0) {
            Example example = new Example(AdminPrivilege.class);
            Example.Criteria or = example.or();
            or.andEqualTo("adminId", adminId);
            int result1 = adminPrivilegeMapper.deleteByExample(example);
            if (result1 > 0) {
                return new ResponseWrap<>(ResponseCode.CODE_200, result1);
            }
        }
        //手动事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return new ResponseWrap<>(ResponseCode.CODE_1103);
    }

    /**
     * 修改系统帐户信息
     *
     * @param accountInfoVo
     * @return
     */
    @Override
    @Transactional
    public ResponseWrap<String> updateSystemUser(AccountInfoVo accountInfoVo) {
        if (accountInfoVo.getAccountId() == null || accountInfoVo.getAccountId() == 0) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        Admin admin = adminMapper.selectByPrimaryKey(accountInfoVo.getAccountId());
        if (admin == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1102);
        }

        //查询角色是否存在
        AdminGroup adminGroup = adminGroupMapper.selectByPrimaryKey(accountInfoVo.getRoleId());
        if (adminGroup == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1102);
        }

        //重新加密密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encrptPassword = bCryptPasswordEncoder.encode(accountInfoVo.getPassword());

        admin.setUserName(accountInfoVo.getUserAccount());
        admin.setPasswd(encrptPassword);
        admin.setContact(accountInfoVo.getContact());
        admin.setPhoneNumber(accountInfoVo.getPhoneNumber());
        //更新用户信息
        int result = adminMapper.updateByPrimaryKeySelective(admin);
        if (result > 0) {
            Example example = new Example(AdminPrivilege.class);
            Example.Criteria or = example.or();
            or.andEqualTo("adminId", accountInfoVo.getAccountId());
            List<AdminPrivilege> adminPrivileges = adminPrivilegeMapper.selectByExample(example);
            AdminPrivilege adminPrivilege = adminPrivileges.get(0);
            //查询角色是否修改
            if (adminPrivilege.getGroupId().compareTo(accountInfoVo.getRoleId()) == 0) {
                return new ResponseWrap<>(ResponseCode.CODE_200);
            }

            adminPrivilege.setGroupId(accountInfoVo.getRoleId());
            int result1 = adminPrivilegeMapper.updateByPrimaryKeySelective(adminPrivilege);
            if (result1 > 0) {
                return new ResponseWrap<>(ResponseCode.CODE_200, "角色修改成功，请重新登陆账号就可获取对应权限");
            }
        }
        //手动事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return new ResponseWrap<>(ResponseCode.CODE_1103);
    }

    /**
     * 添加角色信息
     *
     * @param roleName 角色名称
     * @return
     */
    @Override
    public ResponseWrap<Integer> addRole(String roleName) {
        if (roleName == null || "".equals(roleName)) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        AdminGroup adminGroup = new AdminGroup();
        adminGroup.setCreateTime(new Date());
        adminGroup.setGroupName(roleName);
        return new ResponseWrap<>(ResponseCode.CODE_200, adminGroupMapper.insert(adminGroup));
    }


    /**
     * 查询角色列表
     *
     * @return
     */
    @Override
    public ResponseWrap<List<RoleInfoVo>> findRoleInfo() {

        List<AdminGroup> adminGroups = adminGroupMapper.selectAll();
        if (adminGroups == null || adminGroups.size() == 0) {
            return new ResponseWrap<>(ResponseCode.CODE_200);
        }
        List<AdminRes> adminRes = adminResMapper.selectAll();
        //重新组装数据
        HashMap<Long, CompetenceRes> longCompetenceResHashMap = new HashMap<>();
        adminRes.forEach(x -> {
            CompetenceRes competenceRes = new CompetenceRes();
            competenceRes.setResId(x.getId());
            competenceRes.setResName(x.getResourceName());
            competenceRes.setResDescription(x.getResourceComt());
            competenceRes.setArea(x.getArea());
            longCompetenceResHashMap.put(x.getId(), competenceRes);
        });
        //查询所有角色资源对应
        List<AdminGr> adminGrs = adminGrMapper.selectAll();

        //处理组对应角色数据
        HashMap<Long, HashSet<Long>> longHashSetHashMap = buildData(adminGrs);
        List<RoleInfoVo> roleInfoVos = new ArrayList<>(adminGroups.size());
        adminGroups.forEach(adminGroup -> {
            //过滤组对应的角色数据
            HashSet<Long> longs = longHashSetHashMap.get(adminGroup.getId());
            //重新组装数据
            List<CompetenceRes> competenceResList = new ArrayList<>();
            if (longs != null) {
                longs.forEach(x -> {
                    competenceResList.add(longCompetenceResHashMap.get(x));
                });
            }
            RoleInfoVo roleInfoVo = new RoleInfoVo();
            roleInfoVo.setCompetenceRes(competenceResList);
            roleInfoVo.setRoleId(adminGroup.getId());
            roleInfoVo.setRoleName(adminGroup.getGroupName());
            roleInfoVos.add(roleInfoVo);
        });
        return new ResponseWrap<>(ResponseCode.CODE_200, roleInfoVos);
    }

    /**
     * 构建数据
     *
     * @param adminGrs
     * @return
     */
    private HashMap<Long, HashSet<Long>> buildData(List<AdminGr> adminGrs) {
        if (adminGrs == null || adminGrs.size() == 0) {
            return null;
        }
        //处理组对应角色数据
        HashMap<Long, HashSet<Long>> longHashSetHashMap = new HashMap<>();

        adminGrs.forEach(adminGr -> {
            HashSet<Long> longs = longHashSetHashMap.get(adminGr.getGid());
            if (longs == null) {
                longs = new HashSet<>();
                longs.add(adminGr.getRid());
            } else {
                longs.add(adminGr.getRid());
            }
            longHashSetHashMap.put(adminGr.getGid(), longs);
        });
        return longHashSetHashMap;
    }

    /**
     * 更新角色权限
     *
     * @param roleInfoVos
     * @return
     */
    @Override
    public ResponseWrap<Integer> updateRoleInfos(List<RoleInfoVo> roleInfoVos) {
        //获取所有变动角色
        List<Long> roleIds = roleInfoVos.stream().map(x -> x.getRoleId()).collect(Collectors.toList());
        //查询所有角色资源对应
        List<AdminGr> adminGrs = adminGrMapper.selectAll();
        //查询所有资源
        List<Long> resIds = adminResMapper.selectAll().stream().map(x -> x.getId()).collect(Collectors.toList());
        //处理组对应角色数据
        HashMap<Long, HashSet<Long>> longHashSetHashMap = buildData(adminGrs);
        HashMap<Long, HashSet<Long>> updateRoleRec = new HashMap<>(roleInfoVos.size());
        roleInfoVos.forEach(x -> {
            HashSet<Long> collect = (HashSet<Long>) x.getCompetenceRes().stream().map(y -> y.getResId()).collect(Collectors.toSet());
            updateRoleRec.put(x.getRoleId(), collect);
        });

        roleIds.forEach(x -> {
            //需要增加的
            List<Long> addRec = new ArrayList<>();
            //需要删除的
            List<Long> deleteRec = new ArrayList<>();
            //数据库存储的权限
            HashSet<Long> dbRec = longHashSetHashMap.get(x);
            //需要更新的数据
            HashSet<Long> updateRec = updateRoleRec.get(x);
            updateRec.forEach(rec -> {
                if (dbRec == null || !dbRec.contains(rec)) {
                    if (resIds.contains(rec)) {
                        addRec.add(rec);
                    }
                }
            });
            if (dbRec != null) {
                dbRec.forEach(db -> {
                    if (updateRec == null || !updateRec.contains(db)) {
                        deleteRec.add(db);
                    }
                });
            }
            //删除已去除权限资源
            if (deleteRec != null && deleteRec.size() != 0) {
                adminGrMapper.deleteRoleRec(x, deleteRec);
            }
            //添加新增资源
            if (addRec != null && addRec.size() != 0) {
                adminGrMapper.insertRoleRec(x, addRec);
            }
        });
        return new ResponseWrap<>(ResponseCode.CODE_200);
    }

    /**
     * 查询权限资源
     *
     * @return
     */
    @Override
    public ResponseWrap<List<CompetenceRes>> findCompetenceRec() {
        List<AdminRes> adminRes = adminResMapper.selectAll();
        List<CompetenceRes> competenceResList = new ArrayList<>();
        adminRes.forEach(x -> {
            CompetenceRes competenceRes = new CompetenceRes();
            competenceRes.setResId(x.getId());
            competenceRes.setResName(x.getResourceName());
            competenceRes.setResDescription(x.getResourceComt());
            competenceRes.setArea(x.getArea());
            competenceResList.add(competenceRes);
        });
        return new ResponseWrap<>(ResponseCode.CODE_200, competenceResList);
    }

    /**
     * 根据名称查询用户Id
     *
     * @param userName
     * @return
     */
    @Override
    public Long findAccountIdFromUserName(String userName) {
        Admin admin = adminMapper.selectAuthByUsername(userName);
        if (admin == null) {
            return null;
        }
        return admin.getId();
    }

    /**
     * 修改用户信息
     *
     * @param passWord    密码
     * @param phoneNumber 手机号码
     * @param accountId   用户ID
     * @return
     */
    @Override
    public ResponseWrap<Integer> updateAdminUserInfo(String passWord, String phoneNumber, Long accountId) {
        Admin admin = new Admin();
        admin.setId(accountId);
        if (passWord != null && !"".equals(passWord)) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encrptPassword = bCryptPasswordEncoder.encode(passWord);
            admin.setPasswd(encrptPassword);
        }
        if (phoneNumber != null && !"".equals(phoneNumber)) {
            admin.setPhoneNumber(phoneNumber);
        }
        return new ResponseWrap<>(ResponseCode.CODE_200, adminMapper.updateByPrimaryKeySelective(admin));
    }


}

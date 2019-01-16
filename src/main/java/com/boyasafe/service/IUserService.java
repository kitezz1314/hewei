package com.boyasafe.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.model.User;
import com.boyasafe.model.vo.UserVo;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

    List<User> selectByLoginName(UserVo userVo);

    void insertByVo(UserVo userVo);

    UserVo selectVoById(Long id);

    void updateByVo(UserVo userVo);

    void updatePwdByUserId(Long userId, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(Long id);
    
    Map<String, Object> selectUserByCode(String code);
    
    List<User> selectCombobox();
    
    User selectUserByLoginName(String code);
}
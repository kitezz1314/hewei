package com.hewei.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.hewei.commons.result.Tree;
import com.hewei.commons.shiro.ShiroUser;
import com.hewei.model.Resource;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IResourceService extends IService<Resource> {

    List<Resource> selectAll();

    List<Tree> selectAllMenu();

    List<Tree> selectAllTree();

    List<Tree> selectTree(ShiroUser shiroUser);

}
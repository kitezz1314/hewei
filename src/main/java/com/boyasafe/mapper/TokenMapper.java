package com.boyasafe.mapper;

import com.boyasafe.model.Token;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenzhuo
 * @since 2018-06-07
 */
public interface TokenMapper {
	Token selectById(Long id);
	boolean updateById(Token token);
}
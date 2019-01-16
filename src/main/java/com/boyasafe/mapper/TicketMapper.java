package com.boyasafe.mapper;

import com.boyasafe.model.Ticket;
/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenzhuo
 * @since 2018-06-07
 */
public interface TicketMapper {
	Ticket selectById(Long id);
	boolean updateById(Ticket ticket);
}
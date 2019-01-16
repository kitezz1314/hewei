package com.hewei.model;

import java.util.Date;
/**
 * <p>
 * 
 * </p>
 *
 * @author chenzhuo
 * @since 2018-06-07
 */
public class Ticket  {
	private String ticketid;
	private String ticket;
	private Long expiresIn;
	private Date createtime;
	public String getTicketid() {
		return ticketid;
	}
	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}

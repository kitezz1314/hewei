package com.boyasafe.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-23
 */
public class Clean extends Model<Clean> {

    private static final long serialVersionUID = 1L;

    @TableId("clean_id")
	private Long cleanId;
	@TableField("machine_id")
	private String machineId;
	private String cleaner;
    /**
     * 清洁卡清洁-0
            清洁打印头-1
            清洁滚轮-2
     */
	@TableField("clean_type")
	private Integer cleanType;
	@TableField("clean_time")
	private Date cleanTime;
	private String by1;
	private String by2;
	private String by3;
	private String by4;


	public Long getCleanId() {
		return cleanId;
	}

	public void setCleanId(Long cleanId) {
		this.cleanId = cleanId;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getCleaner() {
		return cleaner;
	}

	public void setCleaner(String cleaner) {
		this.cleaner = cleaner;
	}

	public Integer getCleanType() {
		return cleanType;
	}

	public void setCleanType(Integer cleanType) {
		this.cleanType = cleanType;
	}

	public Date getCleanTime() {
		return cleanTime;
	}

	public void setCleanTime(Date cleanTime) {
		this.cleanTime = cleanTime;
	}

	public String getBy1() {
		return by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public String getBy2() {
		return by2;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	public String getBy3() {
		return by3;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}

	public String getBy4() {
		return by4;
	}

	public void setBy4(String by4) {
		this.by4 = by4;
	}

	@Override
	protected Serializable pkVal() {
		return this.cleanId;
	}

}

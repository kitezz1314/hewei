package com.boyasafe.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;


/**
 * <p>
 * 
 * </p>
 *
 * @author chenzhuo
 * @since 2018-06-19
 */
public class Malfunction{


	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	@TableField("machine_id")
	private String machineId;
	@TableField("net_name")
	private String netName;
	@TableField("question_des")
	private String questionDes;
	private String phone;
	@TableField("reserve_time")
	private Date reserveTime;
	private String province;
	private String city;
	private String county;
	private byte[] image1;
	private byte[] image2;
	private byte[] image3;
	private String repairman;
    /**
     * 0-未派单
            1-已派单
            2-在途中
            3-维修中
            4-已完成
     */
	private String status;
	@TableField("create_time")
	private Date createTime;
	//用户openid
	private String by1;
	//硬件工程师openid
	private String by2;
	private String by3;
	private String by4;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public String getQuestionDes() {
		return questionDes;
	}

	public void setQuestionDes(String questionDes) {
		this.questionDes = questionDes;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public String getRepairman() {
		return repairman;
	}

	public void setRepairman(String repairman) {
		this.repairman = repairman;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}

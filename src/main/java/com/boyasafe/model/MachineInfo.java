package com.boyasafe.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-22
 */
@TableName("machine_info")
public class MachineInfo extends Model<MachineInfo>  {

    private static final long serialVersionUID = 1L;

    @TableId("machine_id")
	private String machineId;
	@TableField("machine_type")
	private String machineType;
	@TableField("machine_number")
	private String machineNumber;
	private String province;
	private String city;
	private String county;
	@TableField("software_name")
	private String softwareName;
	@TableField("software_version")
	private String softwareVersion;
	@TableField("install_dir")
	private String installDir;
	@TableField("install_time")
	private Date installTime;
	private String installer;
	@TableField("installer_phone")
	private String installerPhone;
	private String operator;
	@TableField("operator_phone")
	private String operatorPhone;
	private String principle;
	@TableField("principle_phone")
	private String principlePhone;
	@TableField("net_name")
	private String netName;


	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getMachineNumber() {
		return machineNumber;
	}

	public void setMachineNumber(String machineNumber) {
		this.machineNumber = machineNumber;
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

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getInstallDir() {
		return installDir;
	}

	public void setInstallDir(String installDir) {
		this.installDir = installDir;
	}

	public Date getInstallTime() {
		return installTime;
	}

	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}

	public String getInstaller() {
		return installer;
	}

	public void setInstaller(String installer) {
		this.installer = installer;
	}

	public String getInstallerPhone() {
		return installerPhone;
	}

	public void setInstallerPhone(String installerPhone) {
		this.installerPhone = installerPhone;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorPhone() {
		return operatorPhone;
	}

	public void setOperatorPhone(String operatorPhone) {
		this.operatorPhone = operatorPhone;
	}

	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	public String getPrinciplePhone() {
		return principlePhone;
	}

	public void setPrinciplePhone(String principlePhone) {
		this.principlePhone = principlePhone;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	@Override
	protected Serializable pkVal() {
		return this.machineId;
	}

}

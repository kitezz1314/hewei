package com.boyasafe.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("machine_problems")
public class MachineProblems extends Model<MachineProblems> {

    private static final long serialVersionUID = 1L;

	@TableId(value="problem_id", type= IdType.AUTO)
	private Long problemId;
	@TableField("machine_id")
	private String machineId;
	private String seller;
	@TableField("seller_phone")
	private String sellerPhone;
	@TableField("question_behave")
	private String questionBehave;
	@TableField("question_solve")
	private String questionSolve;
	@TableField("up_door")
	private String upDoor;
	private String fit;
	@TableField("fit_id")
	private String fitId;
	@TableField("fit_name")
	private String fitName;
	@TableField("question_status")
	private String questionStatus;
	@TableField("end_time")
	private Date endTime;
	@TableField("begin_time")
	private Date beginTime;


	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public String getQuestionBehave() {
		return questionBehave;
	}

	public void setQuestionBehave(String questionBehave) {
		this.questionBehave = questionBehave;
	}

	public String getQuestionSolve() {
		return questionSolve;
	}

	public void setQuestionSolve(String questionSolve) {
		this.questionSolve = questionSolve;
	}

	public String getUpDoor() {
		return upDoor;
	}

	public void setUpDoor(String upDoor) {
		this.upDoor = upDoor;
	}

	public String getFit() {
		return fit;
	}

	public void setFit(String fit) {
		this.fit = fit;
	}

	public String getFitId() {
		return fitId;
	}

	public void setFitId(String fitId) {
		this.fitId = fitId;
	}

	public String getFitName() {
		return fitName;
	}

	public void setFitName(String fitName) {
		this.fitName = fitName;
	}

	public String getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.problemId;
	}

}

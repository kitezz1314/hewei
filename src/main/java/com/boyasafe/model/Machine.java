package com.boyasafe.model;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-20
 */
public class Machine implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("code")
	private String code;
	private String note;
	private String pycode;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPycode() {
		return pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}

	

}

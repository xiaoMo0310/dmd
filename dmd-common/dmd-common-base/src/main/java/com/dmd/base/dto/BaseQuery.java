package com.dmd.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/15 18:32
 * @Description 
 */

@Data
public class BaseQuery implements Serializable {

	private static final long serialVersionUID = 3319698607712846427L;

	/**
	 * 当前页
	 */
	private Integer pageNum = 1;

	/**
	 * 每页条数
	 */
	private Integer pageSize = 10;

	/**
	 * 排序 例:xxx asc, xxx desc
	 */
	private String orderBy = "update_time desc";
/*	*//**
	 * 排序 例:xxx asc, xxx desc
	 *//*
	private boolean beCheck=false;
	*//**
	 * 排序 例:xxx asc, xxx desc
	 *//*
	private Long roleId;

	private Long adminId;*/


}

package com.dmd.base.enums;


/**
 * The class Error code enum.
 *
 * @author paascloud.net @gmail.com
 */
public enum ErrorCodeEnum {
	/**
	 * Gl 99990100 error code enum.
	 */
	GL99990100(9999100, "参数异常"),
	/**
	 * Gl 99990401 error code enum.
	 */
	GL99990401(99990401, "无访问权限"),
	/**
	 * Gl 000500 error code enum.
	 */
	GL99990500(500, "未知异常"),
	/**
	 * Gl 000403 error code enum.
	 */
	GL99990403(9999403, "无权访问"),
	/**
	 * Gl 000404 error code enum.
	 */
	GL9999404(9999404, "找不到指定资源"),
	/**
	 * Gl 99990001 error code enum.
	 */
	GL99990001(99990001, "注解使用错误"),
	/**
	 * Gl 99990002 error code enum.
	 */
	GL99990002(99990002, "微服务不在线,或者网络超时"),
	/**
	 * UMS 10010001 error code enum.
	 */
//	 1001 用户中心
	UMS10010001(10010001, "会话超时,请刷新页面重试"),
	/**
	 * UMS 10010002 error code enum.
	 */
	UMS10010002(10010002, "TOKEN解析失败"),
	/**
	 * UMS 10010003 error code enum.
	 */
	UMS10010003(10010003, "操作频率过快, 您的帐号已被冻结"),
	/**
	 * UMS 10011001 error code enum.
	 */
	UMS10011001(10011001, "用户Id不能为空"),
	/**
	 * UMS 10011002 error code enum.
	 */
	UMS10011002(10011002, "找不到用户,loginName=%s"),
	/**
	 * UMS 10011003 error code enum.
	 */
	UMS10011003(10011003, "找不到用户,userId=%s"),
	/**
	 * UMS 10011004 error code enum.
	 */
	UMS10011004(10011004, "找不到用户,email=%s"),
	/**
	 * UMS 10011006 error code enum.
	 */
	UMS10011006(10012006, "手机号不能为空"),
	/**
	 * UMS 10011007 error code enum.
	 */
	UMS10011007(10011007, "登录名不能为空"),
	/**
	 * UMS 10011008 error code enum.
	 */
	UMS10011008(10011008, "新密码不能为空"),
	/**
	 * UMS 10011009 error code enum.
	 */
	UMS10011009(10011009, "确认密码不能为空"),
	/**
	 * UMS 10011010 error code enum.
	 */
	UMS10011010(10011010, "两次密码不一致"),
	/**
	 * UMS 10011011 error code enum.
	 */
	UMS10011011(10011011, "用户不存在, userId=%s"),
	/**
	 * UMS 10011012 error code enum.
	 */
	UMS10011012(10011012, "登录名已存在"),
	/**
	 * UMS 10011013 error code enum.
	 */
	UMS10011013(10011013, "手机号已存在"),
	/**
	 * UMS 10011014 error code enum.
	 */
	UMS10011014(10011014, "密码不能为空"),
	/**
	 * UMS 10011016 error code enum.
	 */
	UMS10011016(10011016, "用户名或密码错误"),
	/**
	 * UMS 10011017 error code enum.
	 */
	UMS10011017(10011017, "验证类型错误"),
	/**
	 * UMS 10011018 error code enum.
	 */
	UMS10011018(10011018, "邮箱不能为空"),
	/**
	 * UMS 10011019 error code enum.
	 */
	UMS10011019(10011019, "邮箱已存在"),
	/**
	 * UMS 10011020 error code enum.
	 */
	UMS10011020(10011020, "短信模板不能为空"),
	/**
	 * UMS 10011021 error code enum.
	 */
	UMS10011021(10011021, "发送短信验证码对象转换为json字符串失败"),
	/**
	 * UMS 10011022 error code enum.
	 */
	UMS10011022(10011022, "发送短信验证码失败"),
	/**
	 * UMS 10011023 error code enum.
	 */
	UMS10011023(10011023, "越权操作"),
	/**
	 * UMS 10011024 error code enum.
	 */
	UMS10011024(10011024, "找不到绑定的用户, userId=%"),
	/**
	 * UMS 10011025 error code enum.
	 */
	UMS10011025(10011025, "用户已存在, loginName=%"),
	/**
	 * UMS 10011026 error code enum.
	 */
	UMS10011026(10011026, "更新用户失败, userId=%"),
	/**
	 * UMS 10011027 error code enum.
	 */
	UMS10011027(10011027, "找不到用户,mobile=%s"),
	/**
	 * UMS 10011028 error code enum.
	 */
	UMS10011028(10011028, "链接已失效"),
	/**
	 * UMS 10011029 error code enum.
	 */
	UMS10011029(10011029, "重置密码失败"),
	/**
	 * UMS 10011030 error code enum.
	 */
	UMS10011030(10011030, "激活失败, 链接已过期"),
	/**
	 * UMS 10011031 error code enum.
	 */
	UMS10011031(10011031, "验证码超时, 请重新发送验证码"),
	/**
	 * UMS 10011032 error code enum.
	 */
	UMS10011032(10011032, "邮箱不存在, loginName=%s,email=%s"),
	/**
	 * UMS 10011033 error code enum.
	 */
	UMS10011033(10011033, "清空该用户常用菜单失败"),
	/**
	 * UMS 10011034 error code enum.
	 */
	UMS10011034(10011034, "不允许操作admin用户"),
	/**
	 * UMS 10011035 error code enum.
	 */
	UMS10011035(10011035, "原始密码输入错误"),
	/**
	 * UMS 10011036 error code enum.
	 */
	UMS10011036(10011036, "新密码和原始密码不能相同"),
	/**
	 * UMS 10011037 error code enum.
	 */
	UMS10011037(10011037, "修改用户失败,userId=%s"),
	/**
	 * UMS 10011038 error code enum.
	 */
	UMS10011038(10011038, "激活用户失败,userId=%s"),
	/**
	 * UMS 10011039 error code enum.
	 */
	UMS10011039(10011039, "验证token失败"),
	/**
	 * UMS 10011040 error code enum.
	 */
	UMS10011040(10011040, "解析header失败"),
	/**
	 * UMS 10011041 error code enum.
	 */
	UMS10011041(10011041, "页面已过期,请重新登录"),
	/**
	 * UMS 10011042 error code enum.
	 */
	UMS10011042(10011042, "Cookie转码异常"),
	/**
	 * UMS 10012001 error code enum.
	 */
	UMS10012001(10012001, "角色ID不能为空"),
	/**
	 * UMS 10012002 error code enum.
	 */
	UMS10012002(10012002, "拥有的角色不允许禁用"),
	/**
	 * UMS 10012003 error code enum.
	 */
	UMS10012003(10012003, "系统角色不能删除"),
	/**
	 * UMS 10012004 error code enum.
	 */
	UMS10012004(10012004, "超级角色Id不能为空"),

	/**
	 * UMS 10012005 error code enum.
	 */
	UMS10012005(10012005, "找不到角色信息,roleId=%s"),
	/**
	 * UMS 10012006 error code enum.
	 */
	UMS10012006(10012006, "删除角色失败, roleId=%s"),
	/**
	 * UMS 10012007 error code enum.
	 */
	UMS10012007(10012007, "批量删除角色失败, roleId=%s"),
	/**
	 * UMS 10012008 error code enum.
	 */
	UMS10012008(10012008, "找不到绑定的角色, roleId=%s"),


	/**
	 * UMS 10013001 error code enum.
	 */
	UMS10013001(10013001, "父菜单不存在,menuId=%s"),
	/**
	 * UMS 10013002 error code enum.
	 */
	UMS10013002(10013002, "更新上级菜单失败,menuId=%s"),
	/**
	 * UMS 10013003 error code enum.
	 */
	UMS10013003(10013003, "菜单不存在,menuId=%s"),
	/**
	 * UMS 10013004 error code enum.
	 */
	UMS10013004(10013004, "启用菜单失败,menuId=%s"),
	/**
	 * UMS 10013005 error code enum.
	 */
	UMS10013005(10013005, "禁用菜单失败,menuId=%s"),
	/**
	 * UMS 10013006 error code enum.
	 */
	UMS10013006(10013006, "更新菜单状态失败,menuId=%s"),
	/**
	 * UMS 10013007 error code enum.
	 */
	UMS10013007(10013007, "根菜单不能禁用"),
	/**
	 * UMS 10013008 error code enum.
	 */
	UMS10013008(10013008, "删除菜单失败, menuId=%s"),
	/**
	 * UMS 10013009 error code enum.
	 */
	UMS10013009(10013009, "请先分配菜单"),
	/**
	 * UMS 10013010 error code enum.
	 */
	UMS10013010(10013010, "选择菜单不是根目录,menuId=%s"),


	/**
	 * UMS 10014001 error code enum.
	 */
	UMS10014001(10014001, "找不到权限信息, actionId=%s"),
	/**
	 * UMS 10014002 error code enum.
	 */
	UMS10014002(10014002, "删除失败, actionId=%s"),
	/**
	 * UMS 10014003 error code enum.
	 */
	UMS10014003(10014003, "保存权限信息失败"),
	/**
	 * UMS 10015001 error code enum.
	 */
	UMS10015001(10015001, "找不到组织信息,groupId=%s"),
	/**
	 * UMS 10015002 error code enum.
	 */
	UMS10015002(10015002, "组织状态不存在"),
	/**
	 * UMS 10015003 error code enum.
	 */
	UMS10015003(10015003, "操作越权, 启用子节点, 必须先启用父节点"),
	/**
	 * UMS 10015004 error code enum.
	 */
	UMS10015004(10015004, "找不到组织信息,groupId=%s"),
	/**
	 * UMS 10015006 error code enum.
	 */
	UMS10015006(10015006, "更新组织信息失败,groupId=%s"),
	/**
	 * UMS 10015007 error code enum.
	 */
	UMS10015007(10015007, "该组织下还存在子节点，不能将其删除, Pid=%s"),
	/**
	 * UMS 10015008 error code enum.
	 */
	UMS10015008(10015008, "该组织下绑定的用户，不能将其删除, groupId=%s"),
	/**
	 * UMS 10015009 error code enum.
	 */
	UMS10015009(10015009, "找不到上级组织, groupId=%s"),
	/**
	 * UMS 10015010error code enum.
	 */
	UMS10015010(10015010, "找不到教练信息, coachId=%s"),
	/**
	 * PMS 10021001 error code enum.
	 */
// 1002 数据中心
	PMS10021001(10021001, "获取地址信息失败"),
	/**
	 * PMS 10021002 error code enum.
	 */
	PMS10021002(10021002, "找不到该地址信息"),
	/**
	 * PMS 10021003 error code enum.
	 */
	PMS10021003(10021003, "获取商品信息失败"),
	/**
	 * PMS 10021004 error code enum.
	 */
	PMS10021004(10021004, "找不到该商品信息,productId=%s"),
	/**
	 * PMS 10021015 error code enum.
	 */
	PMS10021015(10021015, "商品不是在线售卖状态, productId=%s"),
	/**
	 * PMS 10021016 error code enum.
	 */
	PMS10021016(10021016, "商品库存不足, productId=%s"),
	/**
	 * PMS 10021017 error code enum.
	 */
	PMS10021017(10021017, "产品已下架或者删除"),
	/**
	 * PMS 10021018 error code enum.
	 */
	PMS10021018(10021018, "找不到数据字典信息, dictId=%s"),
	/**
	 * PMS 10021019 error code enum.
	 */
	PMS10021019(10021019, "更新字典状态失败, dictId=%s"),
	/**
	 * PMS 10021020 error code enum.
	 */
	PMS10021020(10021020, "上级数据字典不存在, dictId=%s"),
	/**
	 * PMS 10021021 error code enum.
	 */
	PMS10021021(10021021, "商品ID不能为空"),
	/**
	 * PMS 10021024 error code enum.
	 */
	PMS10021024(10021024, "商品编码不能为空"),

	/**
	 * PMS 10023001 error code enum.
	 */
	PMS10023001(10023001, "找不到商品分类信息, categoryId=%s"),

	/**
	 * PMS 10023002 error code enum.
	 */
	PMS10023002(10023002, "上级商品分类不存在, categoryId=%s"),

	/**
	 * PMS 10023003 error code enum.
	 */
	PMS10023003(10023003, "更新商品分类状态失败, categoryId=%s"),
	/**
	 * PMS 10021022 error code enum.
	 */
	PMS10021022(10021022, "更新商品信息失败, productId=%s"),
	/**
	 * PMS 10021023 error code enum.
	 */
	PMS10021023(10021023, "删除商品信息失败, productId=%s"),
	/**
	 * PMS 10021025 error code enum.
	 */
	PMS10021025(10021025, "找不到指定的证书信息, certificateId=%s"),
	/**
	 * PMS 10021026 error code enum.
	 */
	PMS10021026(10021026, "找不到指定积分商品的信息, productId=%s"),
	/**
	 * PMS 10021027 error code enum.
	 */
	PMS10021027(10021027, "活动开始不能购买"),
	/**
	 * PMS 10021028error code enum.
	 */
	PMS10021028(10021028, "减库存失败"),

	/**
	 * PMS 10021029error code enum.
	 */
	PMS10021029(10021029, "当前时间段有重复的商品活动"),

	/**
	 * PMS 10021030error code enum.
	 */
	PMS10021030(10021030, "暂无此商品信息"),
    /**
	 * PMS 10021031error code enum.
	 */
	PMS10021031(10021031, "查询不到商品规格库存信息, id=%s"),
	/**
	 * PMS 10021032error code enum.
	 */
	PMS10021032(10021032, "减库存失败"),
	/**
	 * PMS 10021033error code enum.
	 */
	PMS10021033(10021033, "产品已售完"),
	/**
	 * PMS 10021034error code enum.
	 */
	PMS10021034(10021034, "请先上传教练证书信息"),
	/**
	 * PMS 10021035error code enum.
	 */
	PMS10021035(10021035, "不能发布高于自己证书等级的产品"),
// 1003 订单中心
	/**
	 * OMS 10031001 error code enum.
	 */
	OMS10031001(10031001, "购物车为空, userId=%s"),
	/**
	 * OMS 10031002 error code enum.
	 */
	OMS10031002(10031002, "生成订单失败"),
	/**
	 * OMS 10031003 error code enum.
	 */
	OMS10031003(10031003, "该用户此订单不存在"),
	/**
	 * OMS 10031004 error code enum.
	 */
	OMS10031004(10031004, "已付款, 无法取消订单"),
	/**
	 * OMS 10031005 error code enum.
	 */
	OMS10031005(10031005, "找不到订单信息, orderNo=%s"),
	/**
	 * OMS 10031006 error code enum.
	 */
	OMS10031006(10031006, "清空购物车失败"),
	/**
	 * OMS 10031007 error code enum.
	 */
	OMS10031007(10031007, "不存在默认地址"),
	/**
	 * OMS 10031008 error code enum.
	 */
	OMS10031008(10031008, "更新默认地址失败, addressId=%s"),
	/**
	 * OMS 10031009 error code enum.
	 */
	OMS10031009(10031009, "批量插入订单明细失败"),
	/**
	 * OMS 10031010 error code enum.
	 */
	OMS10031010(10031010, "非快乐学习网的订单, 回调忽略"),
	/**
	 * OMS 10031011 error code enum.
	 */
	OMS10031011(10031011, "支付宝重复调用"),
	/**
	 * OMS 10031012 error code enum.
	 */
	OMS10031012(10031012, "上传失败"),
	/**
	 * OMS 10031013 error code enum.
	 */
	OMS10031013(10031013, "获取附件地址失败"),
	/**
	 * OMS 10031014 error code enum.
	 */
	OMS10031014(10031014, "更新购物车数据失败, cartId=%s"),
	/**
	 * OMS 10031016 error code enum.
	 */
	OMS10031016(10031016, "更新购物车数据失败, cartId=%s"),
	/**
	 * OMS 10031015 error code enum.
	 */
	OMS10031015(10031015, "使用积分出现错误"),
	/**
	 * OMS 10031017 error code enum.
	 */
	OMS10031017(10031017, "订单商铺金额错误"),
	/**
	 * OMS 10031018 error code enum.
	 */
	OMS10031018(10031018, "订单商铺运费错误"),
	/**
	 * OMS 10031019 error code enum.
	 */
	OMS10031019(10031019, "订单总金额错误"),
	/**
	 * OMS 10031020 error code enum.
	 */
	OMS10031020(10031020, "未查询到订单信息"),
	/**
	 * OMS 10031021 error code enum.
	 */
	OMS10031021(10031021, "积分不够"),

	/**
	 * OMS 10031022 error code enum.
	 */
	OMS10031022(10031022, "非进行中的订单无法完成订单"),
	/**
	 * OMS 10031023 error code enum.
	 */
	OMS10031023(10031023, "订单未完成,无法进行评价"),
	/**
	 * OMS 10031024 error code enum.
	 */
	OMS10031024(10031024, "未支付或活动开始不能申请售后"),
	/**
	 * OMS 10031025 error code enum.
	 */
	OMS10031025(10031025, "已提交售后申请"),
	/**
	 * OMS 10031026 error code enum.
	 */
	OMS10031026(10031026, "修改订单状态失败"),
	/**
	 * OMS 10031027 error code enum.
	 */
	OMS10031027(10031027, "积分商品不能售后"),
	/**
	 * OMS 10031028 error code enum.
	 */
	OMS10031028(10031028, "不能越级购买学证产品"),
	/**
	 * Opc 10040001 error code enum.
	 */
// 1004 对接中心
	OPC10040001(10040001, "根据IP定位失败"),
	/**
	 * Opc 10040002 error code enum.
	 */
	OPC10040002(10040002, "上传文件失败"),
	/**
	 * Opc 10040003 error code enum.
	 */
	OPC10040003(10040003, "文件类型不符"),
	/**
	 * Opc 10040004 error code enum.
	 */
	OPC10040004(10040004, "发送短信失败"),
	/**
	 * Opc 10040005 error code enum.
	 */
	OPC10040005(10040005, "生成邮件消息体失败"),
	/**
	 * Opc 10040006 error code enum.
	 */
	OPC10040006(10040006, "获取模板信息失败"),
	/**
	 * Opc 10040007 error code enum.
	 */
	OPC10040007(10040007, "更新附件失败, id=%s"),
	/**
	 * Opc 10040008 error code enum.
	 */
	OPC10040008(10040008, "找不到该附件信息, id=%s"),
	/**
	 * Opc 10040009 error code enum.
	 */
	OPC10040009(10040009, "上传图片失败"),
	/**
	 * Tpc 10050001 error code enum.
	 */
	OPC10040010(10040010, "文件名不能为空"),
	/**
	 * Opc 10040011 error code enum.
	 */
	OPC10040011(10040011, "今日流量已用尽, 请明天再试"),
	/**
	 * Tpc 10050001 error code enum.
	 */
// 1005 任务中心
	TPC10050001(10050001, "消息的消费Topic不能为空"),
	/**
	 * Tpc 10050002 error code enum.
	 */
	TPC10050002(10050002, "根据消息key查找的消息为空"),
	/**
	 * Tpc 10050003 error code enum.
	 */
	TPC10050003(10050003, "删除消息失败,messageKey=%s"),
	/**
	 * Tpc 10050004 error code enum.
	 */
	TPC10050004(10050004, "消息中心接口异常,message=%s, messageKey=%s"),
	/**
	 * Tpc 10050005 error code enum.
	 */
	TPC10050005(10050005, "目标接口参数不能为空"),
	/**
	 * Tpc 10050006 error code enum.
	 */
	TPC10050006(10050006, "根据任务Id查找的消息为空"),

	/**
	 * Tpc 10050007 error code enum.
	 */
	TPC10050007(10050007, "消息数据不能为空"),
	/**
	 * Tpc 10050008 error code enum.
	 */
	TPC10050008(10050008, "消息体不能为空,messageKey=%s"),
	/**
	 * Tpc 10050009 error code enum.
	 */
	TPC10050009(10050009, "消息KEY不能为空"),
	/**
	 * Tpc 100500010 error code enum.
	 */
	TPC100500010(10050010, "Topic=%s, 无消费者订阅"),
	/**
	 * Tpc 100500011 error code enum.
	 */
	TPC100500011(10050011, "Mq编码转换异常, MessageKey=%s"),
	/**
	 * Tpc 100500012 error code enum.
	 */
	TPC100500012(10050012, "发送MQ失败, MessageKey=%s"),
	/**
	 * Tpc 100500013 error code enum.
	 */
	TPC100500013(10050013, "延迟级别错误, Topic=%s, MessageKey=%s"),
	/**
	 * Tpc 100500014 error code enum.
	 */
	TPC100500014(10050014, "MQ重试三次,仍然发送失败, Topic=%s, MessageKey=%s"),
	/**
	 * Tpc 100500015 error code enum.
	 */
	TPC100500015(10050015, "消息PID不能为空, messageKey=%s"),
	TPC100500016(10050016,"该手机号已经绑定其他账号，请更换手机号");
	private int code;
	private String msg;

	/**
	 * Msg string.
	 *
	 * @return the string
	 */
	public String msg() {
		return msg;
	}

	/**
	 * Code int.
	 *
	 * @return the int
	 */
	public int code() {
		return code;
	}

	ErrorCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * Gets enum.
	 *
	 * @param code the code
	 *
	 * @return the enum
	 */
	public static ErrorCodeEnum getEnum(int code) {
		for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
			if (ele.code() == code) {
				return ele;
			}
		}
		return null;
	}
}

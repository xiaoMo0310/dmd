package com.dmd.admin.model.vo;

import com.dmd.admin.model.domain.UmsNotice;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/29 16:43
 * @Description 分页查询通知信息 vo
 */
@Data
public class NoticeListVo {

    private UmsNotice umsNotice;

    private List<NoticeMarkVo> noticeMarkVos = new ArrayList<>(0);

}

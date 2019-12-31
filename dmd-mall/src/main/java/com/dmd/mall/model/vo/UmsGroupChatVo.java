package com.dmd.mall.model.vo;

import com.dmd.mall.model.domain.UmsGroupChat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/30 13:31
 * @Description 群聊信息 vo
 */
@Data
public class UmsGroupChatVo {

    private List<UmsGroupChat> groupChats = new ArrayList<>(0);

    /**
     * 是需要新创建(1:不需要 2:需要)
     */
    private Integer isNewCreate;

}

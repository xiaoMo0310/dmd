package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.OmsOrderReturnApplyMapper;
import com.dmd.admin.model.domain.OmsOrderReturnApply;
import com.dmd.admin.model.dto.OmsOrderReturnApplyResult;
import com.dmd.admin.model.dto.OmsReturnApplyQueryParam;
import com.dmd.admin.model.dto.OmsUpdateStatusParam;
import com.dmd.admin.service.OmsOrderReturnApplyService;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单退货申请 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsOrderReturnApplyServiceImpl extends BaseService<OmsOrderReturnApply> implements OmsOrderReturnApplyService {

    @Autowired
    private OmsOrderReturnApplyMapper returnApplyMapper;

    @Override
    public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return returnApplyMapper.getList(queryParam);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        Example example = new Example(OmsOrderReturnApply.class);
        example.createCriteria().andIn("id", ids).andEqualTo("status", 3);
        return returnApplyMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if(status.equals(1)){
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setShippingId(statusParam.getShippingId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else if(status.equals(2)){
            //完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else{
            return 0;
        }
        return returnApplyMapper.updateByPrimaryKeySelective(returnApply);
    }

    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        return returnApplyMapper.getDetail(id);
    }
}

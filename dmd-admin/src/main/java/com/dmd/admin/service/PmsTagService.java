package com.dmd.admin.service;

import com.dmd.admin.model.domain.PmsTag;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-16
 */
public interface PmsTagService extends IService<PmsTag> {

    /**
     * 添加或者修改标签
     *
     * @param loginAuthDto
     * @param pmsTag
     * @return
     */
    int saveOrUpdateTag(LoginAuthDto loginAuthDto, PmsTag pmsTag);

    /**
     * 删除标签
     * @param ids
     * @return
     */
    int deleteTag(List<Long> ids);

    /**
     * 修改显示状态
     *
     * @param loginAuthDto
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(LoginAuthDto loginAuthDto, List<Long> ids, Integer showStatus);

    /**
     * 根据id查询标签
     */
    PmsTag findTagById(Long id);

    /**
     * 分页查询标签信息
     * @param pmsTag
     * @return
     */
    PageInfo<PmsTag> findTagListByPage(PmsTag pmsTag);
}

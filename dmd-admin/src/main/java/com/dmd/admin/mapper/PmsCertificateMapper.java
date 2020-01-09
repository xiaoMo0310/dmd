package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.PmsCertificate;
import com.dmd.admin.model.dto.PmsCertificateDto;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 证书数据表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
@Mapper
@Component
public interface PmsCertificateMapper extends MyMapper<PmsCertificate> {

    List<PmsCertificate> selectCertificateList(PmsCertificateDto certificateDto);

    int selectCertificateCount();

    Long countBeforeCertificate(Integer certificateLevel);
}

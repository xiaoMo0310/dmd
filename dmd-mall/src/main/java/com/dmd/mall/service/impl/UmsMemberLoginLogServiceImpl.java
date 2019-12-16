package com.dmd.mall.service.impl;

import com.dmd.GaoDeUtil;
import com.dmd.RedisKeyUtil;
import com.dmd.base.constant.GlobalConstant;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.dto.UserTokenDto;
import com.dmd.core.support.BaseService;
import com.dmd.core.utils.RequestUtil;
import com.dmd.gaode.GaodeLocation;
import com.dmd.mall.mapper.UmsMemberLoginLogMapper;
import com.dmd.mall.model.domain.UmsMemberLoginLog;
import com.dmd.mall.service.UmsMemberLoginLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 会员登录记录 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsMemberLoginLogServiceImpl extends BaseService<UmsMemberLoginLog> implements UmsMemberLoginLogService {

    @Autowired
    private UmsMemberLoginLogMapper umsMemberLoginLogMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveLoginUserLog(String accessToken, String refreshToken, LoginAuthDto loginAuthDto, HttpServletRequest request) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //获取客户端操作系统
        final String os = userAgent.getOperatingSystem().getName();
        //获取客户端浏览器
        final String browser = userAgent.getBrowser().getName();
        //ip
        final String remoteAddr = RequestUtil.getRemoteAddr(request);
        // 根据IP获取位置信息
        String temp = "127.0.";
        String temp2 = "192.168.";
        String temp3 = "0:0:";
        String location = remoteAddr;
        if (location.startsWith(temp) || location.startsWith(temp2) || location.startsWith(temp3)) {
            location = "111.199.188.14";
        }
        GaodeLocation gaodeLocation = GaoDeUtil.getCityByIpAddr(location);
        String remoteLocation = gaodeLocation.getProvince().contains("市") ? gaodeLocation.getCity() : gaodeLocation.getProvince() + GlobalConstant.Symbol.SHORT_LINE + gaodeLocation.getCity();

        UmsMemberLoginLog memberLoginLog = new UmsMemberLoginLog();
        memberLoginLog.setAccessToken(accessToken);
        memberLoginLog.setRefreshToken(refreshToken);
        memberLoginLog.setBrowser(browser);
        memberLoginLog.setOs(os);
        memberLoginLog.setLoginIp(remoteAddr);
        memberLoginLog.setLoginLocation(remoteLocation);
        memberLoginLog.setCreateTime(new Date());
        memberLoginLog.setMemberId(loginAuthDto.getUserId());
        memberLoginLog.setUserType(loginAuthDto.getUserType());
        // 记录登录日志
        umsMemberLoginLogMapper.insertSelective(memberLoginLog);

        int accessTokenValidateSeconds = 999999999;
        int refreshTokenValiditySeconds = 999999999;
        UserTokenDto userTokenDto = new UserTokenDto();
        userTokenDto.setOs(os);
        userTokenDto.setBrowser(browser);
        userTokenDto.setAccessToken(accessToken);
        userTokenDto.setAccessTokenValidity(accessTokenValidateSeconds);
        userTokenDto.setLoginIp(remoteAddr);
        userTokenDto.setLoginLocation(remoteLocation);
        userTokenDto.setRefreshToken(refreshToken);
        userTokenDto.setRefreshTokenValidity(refreshTokenValiditySeconds);
        userTokenDto.setUserId(loginAuthDto.getUserId());
        userTokenDto.setUserName(loginAuthDto.getUserName());
        userTokenDto.setNickName(loginAuthDto.getNickName());
        userTokenDto.setUserType(loginAuthDto.getUserType());
        userTokenDto.setPhone(loginAuthDto.getPhone());
        // 存入redis数据库
        updateRedisUserToken(accessToken, accessTokenValidateSeconds, userTokenDto);
    }

    private void updateRedisUserToken(String accessToken, int accessTokenValidateSeconds, UserTokenDto userTokenDto) {
        redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(accessToken), userTokenDto, accessTokenValidateSeconds, TimeUnit.SECONDS);
    }
}

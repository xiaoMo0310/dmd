package com.dmd;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.dmd.gaode.GaodeLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * The class Gao de util.
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GaoDeUtil {

	/**
	 * Gets city by ip addr.
	 *
	 * @param ipAddr the ip addr
	 *
	 * @return the city by ip addr
	 */
	public static GaodeLocation getCityByIpAddr(String ipAddr) {
		// http://lbs.amap.com/api/webservice/guide/api/ipconfig/
		log.info("getCityByIpAddr - 根据IP定位. ipAddr={}", ipAddr);
		GaodeLocation location = null;
		String urlAddressIp = "http://restapi.amap.com/v3/ip?key=a0702be26e001e719c6a48de11c76568&ip=%s";
		String url = String.format(urlAddressIp, ipAddr);
		try {
			String str = HttpClientUtil.get(HttpConfig.custom().url(url));
			log.info(str);
			location = new ObjectMapper().readValue(str, GaodeLocation.class);
		} catch (Exception e) {
			log.error("getCityByIpAddr={}", e.getMessage(), e);
		}
		log.info("getCityByIpAddr - 根据IP定位. ipAddr={}, location={}", ipAddr, location);
		return location;
	}
}

package com.dadi01.scrm.foundation.assist.filter.dubbo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.dadi01.scrm.foundation.utils.json.SimpleObjectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端默认日志记录
 * 
 * @author summerrains
 *
 */
public class DefaultProviderLoggingImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProviderLoggingImpl.class);
	
	public DefaultProviderLoggingImpl() {
		
	}

	public void before(String traceId,long start,String clientIp, String interfaceName, String methodName,
			Object[] args, Map<String, String> attachments) {
		if (LOGGER.isInfoEnabled()) {
			StringBuilder builder = new StringBuilder();
			builder.append("\n=====================进入服务====================");
			builder.append("\n时间:");
			builder.append(formater(new Date(start)));
			builder.append(" 地址:");
			builder.append(clientIp);
			//builder.append("\n附加:");
			//builder.append(StringUtils.toQueryString(attachments));
			builder.append("\n服务:");
			builder.append(interfaceName);
			builder.append(".");
			builder.append(methodName);
			builder.append("(");
			builder.append(SimpleObjectUtils.objectData2json(args));
			builder.append(")");
			builder.append("\n===============================================");
			LOGGER.info(builder.toString());
		}
	}
	
	public String formater(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return sdf.format(date);
	}

	@SuppressWarnings("rawtypes")
	public void after(String traceId,long start,String clientIp, String interfaceName, String methodName,
			Object[] args, Map<String, String> attachments, Throwable throwable, Object value) {
		long end = System.currentTimeMillis();
		if (throwable != null) {
			if (LOGGER.isErrorEnabled()) {
				StringBuilder builder = new StringBuilder();
				builder.append("\n=====================结束服务====================");
				builder.append("\n时间:");
				builder.append(formater(new Date(end)));
				builder.append(" 地址:");
				builder.append(clientIp);
				//builder.append("\n附加:");
				//builder.append(StringUtils.toQueryString(attachments));
				builder.append("\n服务:");
				builder.append(interfaceName);
				builder.append(".");
				builder.append(methodName);
				builder.append("(");
				builder.append(SimpleObjectUtils.objectData2json(args));
				builder.append(")");
				builder.append("\n异常:\n"+StringUtils.toString(throwable));
				builder.append("\n耗时:");
				builder.append(String.valueOf(end-start));
				builder.append("毫秒");
				builder.append("\n==============================================");
				LOGGER.error(builder.toString());
			}
		} else {
			if (LOGGER.isInfoEnabled()) {
				StringBuilder builder = new StringBuilder();
				builder.append("\n=====================结束服务====================");
				builder.append("\n时间:");
				builder.append(formater(new Date(end)));
				builder.append(" 地址:");
				builder.append(clientIp);
				//builder.append("\n附加:");
				//builder.append(StringUtils.toQueryString(attachments));
				builder.append("\n服务:");
				builder.append(interfaceName);
				builder.append(".");
				builder.append(methodName);
				builder.append("(");
				builder.append(SimpleObjectUtils.objectData2json(args));
				builder.append(")");

				builder.append("\n返回:\n"+SimpleObjectUtils.objectData2json(value));
				builder.append("\n耗时:");
				builder.append(String.valueOf(end-start));
				builder.append("\n毫秒");
				builder.append("\n==============================================");
				LOGGER.info(builder.toString());
			}
		}
	}

}

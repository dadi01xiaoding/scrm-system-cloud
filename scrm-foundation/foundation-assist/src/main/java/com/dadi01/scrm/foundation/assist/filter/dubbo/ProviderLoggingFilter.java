package com.dadi01.scrm.foundation.assist.filter.dubbo;

import java.util.UUID;

import com.dadi01.scrm.foundation.model.error.ErrorCodes;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

/**
 *  服务的日志记录
 * @author summerrains
 *
 */
@Activate(group = CommonConstants.PROVIDER)
public class ProviderLoggingFilter implements Filter {

private DefaultProviderLoggingImpl providerLoggingImpl;
	
	private static final String TRACE_ID = "traceId";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProviderLoggingFilter.class);

	public DefaultProviderLoggingImpl getDefaultProviderLoggingImpl() {
		if(providerLoggingImpl == null) {
			synchronized (this) {
				if(providerLoggingImpl == null) {
					providerLoggingImpl = new DefaultProviderLoggingImpl();
				}
			}
		}
		return providerLoggingImpl;
	}
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String ip = RpcContext.getContext().getRemoteHost();
		
		Result result = null;
		long start = System.currentTimeMillis();
		String traceId = RpcContext.getContext().getAttachment("DADI01-TRACE-ID");
		if(traceId == null || traceId.trim().length() == 0) {
			traceId = UUID.randomUUID().toString();
		}
		//服务提供方，接收到traceId
		MDC.put(TRACE_ID, traceId);
		try {
			getDefaultProviderLoggingImpl().before(traceId,start,ip,  invoker.getInterface().getName(), invocation.getMethodName(),
					invocation.getArguments(), RpcContext.getContext().getAttachments());
			try {
				result = invoker.invoke(invocation);
			} catch(RpcException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage(), e);
				result.setException(e.getCause());
				result.setValue("远程调用异常 ");
				result.setAttachment(ErrorCodes.RPC_ERROR," 远程调用异常"+result.getException().getMessage());
			}
			return result;
		} finally {
			afterLogger(traceId,start,ip,  invoker.getInterface().getName(), invocation.getMethodName(),
					invocation.getArguments(), result);
		}
	}
	
	protected void afterLogger(String traceId,long start,String clientIp,  String interfaceName, String methodName,
			Object[] args, Result result) {
		if(result != null) {
			getDefaultProviderLoggingImpl().after(traceId,start,clientIp, interfaceName, methodName,
					args, result.getAttachments(), result.getException(), result.getValue());
		} else {
			getDefaultProviderLoggingImpl().after(traceId,start,clientIp,  interfaceName, methodName,
					args, null,null,null);
		}
	}
	
}

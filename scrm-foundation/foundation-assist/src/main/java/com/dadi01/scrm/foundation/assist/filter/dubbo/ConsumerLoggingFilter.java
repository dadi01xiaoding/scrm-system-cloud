package com.dadi01.scrm.foundation.assist.filter.dubbo;

import java.util.UUID;

import com.dadi01.scrm.foundation.model.error.ErrorCodes;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;


/**
 * 消费端日志记录
 */
@Activate(group = CommonConstants.CONSUMER)
public class ConsumerLoggingFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerLoggingFilter.class);

	private static final String TRACE_ID = "traceId";

	private DefaultConsumerLoggingImpl defaultConsumerLoggingImpl;


	public DefaultConsumerLoggingImpl getDefaultConsumerLoggingImpl() {
		if(defaultConsumerLoggingImpl == null) {
			defaultConsumerLoggingImpl = new DefaultConsumerLoggingImpl();
		}
		return defaultConsumerLoggingImpl;
	}

	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		long start = System.currentTimeMillis();
		Result result = null;
		String traceId = MDC.get(TRACE_ID);
		if(traceId == null) {
			traceId = UUID.randomUUID().toString().replaceAll("-", "");
		}
		//消费者，传递traceId
		RpcContext.getContext().setAttachment("DADI01-TRACE-ID", traceId);
		try {
			getDefaultConsumerLoggingImpl().before(traceId,start,invoker.getInterface().getName(), invocation.getMethodName(),
					invocation.getArguments(), RpcContext.getContext().getAttachments());
			try {
				result = invoker.invoke(invocation);
			} catch (RpcException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage(), e);
				result.setException(e.getCause());
				result.setValue("远程调用异常");
				result.setAttachment(ErrorCodes.RPC_ERROR,"远程调用异常"+result.getException().getMessage());
			}
			return result;
		} finally {
			afterLogger(traceId,start, invoker.getInterface().getName(), invocation.getMethodName(),
					invocation.getArguments(), result);
//			long end = System.currentTimeMillis();
//			if(end - start > 1000 * 1) {
//				LOGGER.warn(invoker.getInterface().getName()+"."+invocation.getMethodName()+":"+(end - start)+"ms");
//			}
		}
	}

	protected void afterLogger(String traceId,long start, String interfaceName, String methodName,
			Object[] args, Result result) {
		if(result != null) {
			getDefaultConsumerLoggingImpl().after(traceId,start, interfaceName, methodName,
					args, result.getAttachments(), result.getException(), result.getValue());
		} else {
			getDefaultConsumerLoggingImpl().after(traceId,start, interfaceName, methodName,
					args, null,null,null);
		}
	}


}

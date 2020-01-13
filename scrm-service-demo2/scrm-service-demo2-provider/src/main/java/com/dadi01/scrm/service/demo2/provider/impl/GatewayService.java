package com.dadi01.scrm.service.demo2.provider.impl;

import com.dadi01.scrm.foundation.model.dto.ResultDTO;
import com.dadi01.scrm.service.demo1.api.IAccountService;
import com.dadi01.scrm.service.demo1.api.dto.AccountDTO;
import com.dadi01.scrm.service.demo2.api.IGateway;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author fang
 * @Date 2020-01-13 02:45
 **/
@Service
@Component
public class GatewayService implements IGateway {

    @Reference
    private IAccountService accountService;

    @Override
    public ResultDTO<String> getUserName() {
        ResultDTO<AccountDTO> account = accountService.queryAccount("");
        return ResultDTO.success(account.getData().getUsername());
    }
}

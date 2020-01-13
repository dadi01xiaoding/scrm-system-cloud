package com.dadi01.scrm.service.demo1.provider.impl;

import com.dadi01.scrm.foundation.model.dto.ResultDTO;
import com.dadi01.scrm.service.demo1.api.IAccountService;
import com.dadi01.scrm.service.demo1.api.dto.AccountDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author fang
 * @Date 2020-01-13 02:11
 **/
@RefreshScope
@Service
@Component
public class AccountService implements IAccountService {

    @Value("${user.name}")
    private String userName;

    @Override
    public ResultDTO<AccountDTO> queryAccount(String username) {
        AccountDTO dto = new AccountDTO();
        dto.setUsername(this.userName);
        return ResultDTO.success(dto);
    }

    @Override
    public ResultDTO updateAccount(AccountDTO accountDTO) {
        return null;
    }
}

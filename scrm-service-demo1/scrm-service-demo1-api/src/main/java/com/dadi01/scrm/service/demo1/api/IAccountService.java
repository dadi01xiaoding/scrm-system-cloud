package com.dadi01.scrm.service.demo1.api;

import com.dadi01.scrm.foundation.model.dto.ResultDTO;
import com.dadi01.scrm.service.demo1.api.dto.AccountDTO;

/**
 * @author fangming
 * @description
 * @date 2020/1/7 16:56
 */
public interface IAccountService {

    ResultDTO<AccountDTO> queryAccount(String username) ;

    ResultDTO updateAccount(AccountDTO accountDTO) ;
}

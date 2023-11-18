package com.challengedbackend.challengedbackend.Service;


import com.challengedbackend.challengedbackend.DTO.Account.AccountDTO;
import com.challengedbackend.challengedbackend.DTO.User.RegisterDTO;

import java.util.List;

public interface iUsersService {

    RegisterDTO create(RegisterDTO usersDTO);

}
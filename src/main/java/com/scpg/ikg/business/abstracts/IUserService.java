package com.scpg.ikg.business.abstracts;

import com.scpg.ikg.core.entities.User;
import com.scpg.ikg.core.utilities.business.IServiceBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.IResult;

public interface IUserService extends IServiceBase<User> {
    IResult addRoleToUser(String username, String roleName);

    DataResult<User> getByUsername(String username);

    DataResult<User> getById(Integer id);

    IResult existsById(int userId);

    DataResult<User> getUserByEmail(String email);

    IResult existsByEmail(String email);
}

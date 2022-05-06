package com.scpg.ikg.business.abstracts;

import com.scpg.ikg.core.entities.Role;
import com.scpg.ikg.core.utilities.business.IServiceBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.IResult;

public interface IRoleService extends IServiceBase<Role> {
    DataResult<Role> findByName(String roleName);

    IResult existByRoleId(int id);

    DataResult<Role> getAllByUser(String username);

    IResult isAdmin(String username);
}

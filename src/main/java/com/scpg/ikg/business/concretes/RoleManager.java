package com.scpg.ikg.business.concretes;

import com.scpg.ikg.business.abstracts.IRoleService;
import com.scpg.ikg.business.tools.Messages;
import com.scpg.ikg.core.entities.Role;
import com.scpg.ikg.core.utilities.business.BusinessRule;
import com.scpg.ikg.core.utilities.results.*;
import com.scpg.ikg.repo.abstracts.IRoleDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleManager implements IRoleService {

    private final IRoleDao iRoleDao;

    @Override
    public DataResult<Role> findByName(String roleName) {
        Role result = iRoleDao.findByName(roleName);
        if (result == null)
            return new ErrorDataResult<>(Messages.roleNotFound);
        return new SuccesDataResult<>(result);
    }

    @Override
    public IResult existByRoleId(int id) {
        return null;
    }

    @Override
    public DataResult<Role> getAllByUser(String username) {

        return new SuccesDataResult<>(iRoleDao.getAllByUsers_Username(username));
    }

    public IResult isAdmin(String username) {
        List<Role> result = iRoleDao.getAllByUsers_Username(username);
        for (Role role : result) {
            if (role.getName().equals("admin"))
                return new SuccessResult();
        }
        return new ErrorResult();
    }

    @Override
    public DataResult<List<Role>> getAll() {
        return new SuccesDataResult<>(iRoleDao.findAll());
    }

    @Override
    public IResult add(Role role) {
        IResult result = BusinessRule.run(ifExistByRoleName(role.getName()));
        if (result != null)
            return result;
        iRoleDao.save(role);
        return new SuccessResult(Messages.roleAdded);
    }

    @Override
    public IResult update(Role role) {
        iRoleDao.save(role);
        return new SuccessResult(Messages.roleUpdated);
    }

    @Override
    public IResult delete(Role role) {
        iRoleDao.delete(role);
        return new SuccessResult(Messages.roleDeleted);
    }

    @Override
    public DataResult<Role> getById(Integer id) {
        return new SuccesDataResult<>(iRoleDao.getById(id), Messages.roleListed);
    }

    private IResult ifExistByRoleName(String roleName) {
        if (iRoleDao.existsByName(roleName))
            return new ErrorResult(Messages.roleNameAlreadyExist);
        return new SuccessResult();
    }
}

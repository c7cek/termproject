package com.scpg.ikg.business.concretes;

import com.scpg.ikg.business.abstracts.IRoleService;
import com.scpg.ikg.business.abstracts.IUserService;
import com.scpg.ikg.business.tools.Messages;
import com.scpg.ikg.core.entities.Role;
import com.scpg.ikg.core.entities.User;
import com.scpg.ikg.core.utilities.business.BusinessRule;
import com.scpg.ikg.core.utilities.results.*;
import com.scpg.ikg.repo.abstracts.IUserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserManager implements IUserService, UserDetailsService {
    private final IUserDao iUserDao;
    private final IRoleService iRoleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserDao.findByUsername(username);
        if (user == null) {
            log.error("user not found in the database");
            throw new UsernameNotFoundException("user not found in the database");

        } else
            log.info("user found in the database: {}", username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }


    @Override
    public DataResult<List<User>> getAll() {

        return new SuccesDataResult(iUserDao.findAll(), Messages.userListed);
    }

    @Override
    public IResult add(User user) {
        IResult result = BusinessRule.run(
                ifAlreadyExistByUsername(user.getUsername()),
                ifAlreadyExistByEmail(user.getEmail())
        );
        if (result != null)
            return result;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        iUserDao.save(user);
        return new SuccessResult(Messages.userSave);
    }

    @Override
    public IResult update(User user) {
        IResult result = BusinessRule.run(
                isExistById(user.getId()),
                isUpdateableWithEmail(user.getEmail(), user.getId()),
                isUpdateablewithUsername(user.getUsername(), user.getId())
        );
        if (result != null)
            return result;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        iUserDao.save(user);
        return new SuccessResult(Messages.userUpdate);
    }

    @Override
    public IResult delete(User user) {
        IResult result = BusinessRule.run(isExistById(user.getId()));
        if (result != null)
            return result;
        iUserDao.delete(user);
        return new SuccessResult(Messages.userDelete);
    }

    @Override
    public IResult addRoleToUser(String username, String roleName) {

        IResult result = BusinessRule.run(
                isExistByUserName(username),
                isExistByRoleName(roleName),
                ifUserAlreadyHasRole(username, roleName));
        if (result != null)
            return result;
        User user = iUserDao.findByUsername(username);
        Role role = iRoleService.findByName(roleName).getData();
        user.getRoles().add(role);
        iUserDao.save(user);
        return new SuccessResult(Messages.roleAddedToUser);
    }


    @Override
    public DataResult<User> getByUsername(String username) {
        IResult result = BusinessRule.run(isExistByUserName(username));
        if (result != null)
            return new ErrorDataResult<>(result.getMessage());
        return new SuccesDataResult<>(iUserDao.findByUsername(username));
    }

    @Override
    public DataResult<User> getById(Integer id) {
        IResult result = BusinessRule.run(
                isExistById(id)
        );
        if (result != null)
            return new ErrorDataResult<>(result.getMessage());
        return new SuccesDataResult<>(iUserDao.getById(id));
    }

    @Override
    public IResult existsById(int userId) {
        boolean result = iUserDao.existsById(userId);
        if (result)
            return new SuccessResult();
        return new ErrorResult();
    }


    @Override
    public DataResult<User> getUserByEmail(String email) {
        return new SuccesDataResult<>(iUserDao.getByEmail(email), Messages.userListed);
    }

    @Override
    public IResult existsByEmail(String email) {
        User user = iUserDao.getByEmail(email);
        if (user != null)
            return new SuccessResult();
        return new ErrorResult(Messages.userNotFound);
    }



    private IResult ifAlreadyExistByUsername(String username) {
        if (iUserDao.existsByUsername(username))
            return new ErrorResult(Messages.usernameAlreadyInUse);
        return new SuccessResult();
    }

    private IResult ifAlreadyExistByEmail(String email) {
        if (iUserDao.existsByEmail(email))
            return new ErrorResult(Messages.emailAlreadyInUse);
        return new SuccessResult();
    }

    private IResult isExistByRoleName(String roleName) {
        if (!iRoleService.findByName(roleName).isSuccess())
            return new ErrorResult(Messages.roleNotFound);
        return new SuccessResult();
    }

    private IResult isExistById(int id) {

        if (!iUserDao.existsById(id))
            return new ErrorResult(Messages.userNotFound);
        return new SuccessResult();

    }

    private IResult isExistByUserName(String username) {

        if (!iUserDao.existsByUsername(username))
            return new ErrorResult(Messages.userNotFound);
        return new SuccessResult();

    }

    private IResult ifUserAlreadyHasRole(String username, String roleName) {
        User user = iUserDao.findByUsername(username);
        if (user == null)
            return new ErrorResult(Messages.userNotFound);
        else {
            List<Role> roles = user.getRoles();
            if (roles != null) {
                for (Role role : roles) {
                    if (role.getName().equals(roleName))
                        return new ErrorResult(Messages.userAlreadyHasRole);
                }
            }
            return new SuccessResult();
        }
    }

    private IResult isUpdateablewithUsername(String username, int id) {
        User result = iUserDao.findByUsernameAndIdNot(username, id);
        if (result != null)
            return new ErrorResult(Messages.emailAlreadyInUse);
        return new SuccessResult();
    }

    private IResult isUpdateableWithEmail(String email, int id) {

        User result = iUserDao.findByEmailAndIdNot(email, id);
        if (result != null)
            return new ErrorResult(Messages.emailAlreadyInUse);
        return new SuccessResult();


    }


}



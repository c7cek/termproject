package com.scpg.ikg.business.concretes;

import com.scpg.ikg.business.abstracts.IAuthService;
import com.scpg.ikg.business.tools.Messages;
import com.scpg.ikg.core.entities.PasswordResetToken;
import com.scpg.ikg.core.entities.User;
import com.scpg.ikg.core.utilities.business.BusinessRule;
import com.scpg.ikg.core.utilities.results.ErrorResult;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.core.utilities.results.SuccesDataResult;
import com.scpg.ikg.core.utilities.results.SuccessResult;
import com.scpg.ikg.repo.abstracts.IResetTokenDao;
import com.scpg.ikg.repo.abstracts.IUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthManager implements IAuthService {


    private final IUserDao iUserDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IResetTokenDao iResetTokenDao;

    @Override
    public IResult passwordReset(String username, String password, String newPassword) {
        IResult result = BusinessRule.run(isExistByUsernameAndPassword(username, password));
        if (result != null)
            return result;

        User user = iUserDao.findByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        iUserDao.save(user);
        return new SuccesDataResult<>(Messages.passwordChangedSuccessfully);
    }

    @Override
    public IResult passwordResetWithToken(String token, String password) {
        PasswordResetToken myToken = iResetTokenDao.getByToken(token);

        User user = myToken.getUser();
        user.setPassword(passwordEncoder.encode(password));
        myToken.setStatus(false);
        iUserDao.save(user);
        iResetTokenDao.save(myToken);
        return new SuccessResult(Messages.passwordChangedSuccessfully);
    }

    @Override
    public IResult isTokenExpired(String token) {
        if (!iResetTokenDao.existsByToken(token))
            return new ErrorResult(Messages.tokenNotValid);

        PasswordResetToken myToken = iResetTokenDao.getByToken(token);

        if (new Date().after(myToken.getExpiryDate())|| !myToken.isStatus())
            return new ErrorResult(Messages.tokenNotValid);

        return new SuccessResult();

    }


    private IResult isExistByUsernameAndPassword(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        org.springframework.security.core.Authentication authInfo = authenticationManager.authenticate(authenticationToken);
        if (authInfo.isAuthenticated())
            return new SuccessResult();
        return new ErrorResult(Messages.userNotFound);
    }
}

package com.scpg.ikg.business.abstracts;

import com.scpg.ikg.core.utilities.results.IResult;

public interface IAuthService {
    IResult passwordReset(String username, String password, String newPassword);

    IResult passwordResetWithToken(String token, String password);

    IResult isTokenExpired(String token);

}

package com.scpg.ikg.business.abstracts;

import com.scpg.ikg.core.entities.PasswordResetToken;
import com.scpg.ikg.core.utilities.business.IServiceBase;
import com.scpg.ikg.core.utilities.results.IResult;

import javax.mail.MessagingException;

public interface IResetTokenService {

    IResult sendPasswordResetLink(String email) throws MessagingException;

}

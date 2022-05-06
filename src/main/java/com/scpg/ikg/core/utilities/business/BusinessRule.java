package com.scpg.ikg.core.utilities.business;

import com.scpg.ikg.core.utilities.results.IResult;

public class BusinessRule {
    public static IResult run(IResult... logics) {
        for (IResult logic : logics) {
            if (!logic.isSuccess())
                return logic;
        }
        return null;
    }
}

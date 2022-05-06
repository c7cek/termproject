package com.scpg.ikg.core.utilities.business;

import com.scpg.ikg.core.entities.IEntity;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.IResult;

import java.util.List;

public interface IServiceBase<T extends IEntity> {
    DataResult<List<T>> getAll();

    IResult add(T t);

    IResult update(T t);

    IResult delete(T t);

    DataResult<T> getById(Integer id);

}

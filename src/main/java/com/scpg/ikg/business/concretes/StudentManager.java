package com.scpg.ikg.business.concretes;

import com.scpg.ikg.business.abstracts.IStudentService;
import com.scpg.ikg.business.tools.Messages;
import com.scpg.ikg.core.utilities.business.BusinessRule;
import com.scpg.ikg.core.utilities.results.*;
import com.scpg.ikg.entities.concretes.Student;
import com.scpg.ikg.entities.dtos.StudentDetailDto;
import com.scpg.ikg.entities.dtos.StudentEditDto;
import com.scpg.ikg.repo.abstracts.IStudentDao;
import com.scpg.ikg.repo.abstracts.IUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentManager implements IStudentService {
    private final IStudentDao iStudentDao;
    private final PasswordEncoder passwordEncoder;
    private final IUserDao iUserDao;


    @Override
    public DataResult<List<Student>> getAll() {
        return new SuccesDataResult<>(iStudentDao.findAll());
    }

    @Override
    public IResult add(Student student) {
        IResult result = BusinessRule.run(
                ifExistByUsername(student.getUsername()),
                ifExistByEmail(student.getEmail())
        );
        if (result != null)
            return result;

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        iStudentDao.save(student);
        return new SuccessResult(Messages.studentAdd);
    }

    @Override
    public IResult update(Student student) {
        IResult result = BusinessRule.run(
                ifExistByEmailIdNot(student.getUsername(), student.getId()),
                ifExistByUsernameIdNot(student.getUsername(), student.getId())

        );
        if (result != null)
            return result;

        Student std = iStudentDao.getById(student.getId());
        std.setName(student.getName());
        std.setLastName(student.getLastName());
        std.setEmail(student.getEmail());
        std.setUsername(student.getUsername());
        iStudentDao.save(std);
        return new SuccessResult(Messages.studentUpdate);
    }

    @Override
    public IResult delete(Student student) {
        iStudentDao.delete(student);
        return new SuccessResult(Messages.studentDelete);
    }

    @Override
    public DataResult<Student> getById(Integer id) {

        return new SuccesDataResult<>(iStudentDao.getById(id), Messages.studentListed);
    }

    @Override
    public IResult existById(int studentId) {
        return null;
    }

    @Override
    public DataResult<StudentDetailDto> getStudentDetailDto(String username) {
        return new SuccesDataResult<>(iStudentDao.getStudentDetailDto(username), Messages.studentListed);
    }

    @Override
    public DataResult<StudentEditDto> getStudentDtoByUsername(String username) {
        return new SuccesDataResult<>(iStudentDao.getStudentDtoByUsername(username));
    }


    private IResult ifExistByUsername(String username) {
        if (iUserDao.existsByUsername(username))
            return new ErrorResult(Messages.usernameAlreadyInUse);

        return new SuccessResult();
    }

    private IResult ifExistByEmail(String email) {
        if (iUserDao.existsByEmail(email))
            return new ErrorResult(Messages.emailAlreadyInUse);
        return new SuccessResult();
    }

    private IResult ifExistByEmailIdNot(String email, int id) {
        if (iUserDao.findByEmailAndIdNot(email, id) != null )
            return new ErrorResult(Messages.emailAlreadyInUse);
        return new SuccessResult();
    }

    private IResult ifExistByUsernameIdNot(String username, int id) {
        if (iUserDao.findByUsernameAndIdNot(username, id)!=null)
            return new ErrorResult(Messages.usernameAlreadyInUse);
        return new SuccessResult();
    }
}

package com.imorate.springsystem.configuration.validator;

import com.imorate.springsystem.model.common.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Student student = (Student) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "student.studentID", "NotEmpty");
        if (String.valueOf(student.getStudentID()).length() != 8) {
            errors.rejectValue("student.studentID", "Size.student.studentID");
        }
    }
}

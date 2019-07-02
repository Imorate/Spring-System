package com.imorate.springsystem.configuration.validator;

import com.imorate.springsystem.model.auth.UserProfile;
import com.imorate.springsystem.model.common.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserProfile userProfile = (UserProfile) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userProfile.phoneNumber", "NotEmpty");
        if (userProfile.getPhoneNumber().length() != 11) {
            errors.rejectValue("userProfile.phoneNumber", "Size.userProfile.phoneNumber");
        }
    }
}

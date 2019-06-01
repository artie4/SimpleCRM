package app.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        boolean valid = true;

        try {
            final Object firstObj = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);

            final Object secondObj = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (Exception exc)
        {

        }

        if(!valid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}

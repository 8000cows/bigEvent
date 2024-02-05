package example.horse.validation;

import example.horse.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Created by LiuSheng at 2024/1/30 12:40
 */


public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return "已发布".equals(value) || "草稿".equals(value) || "".equals(value);
    }
}

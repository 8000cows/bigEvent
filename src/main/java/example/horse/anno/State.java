package example.horse.anno;

import example.horse.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by LiuSheng at 2024/1/30 12:37
 */

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = StateValidation.class)
public @interface State {
    String message() default "参数state的值只能是：草稿 | 已发布";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

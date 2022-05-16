package io.github.prluciohermano.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.github.prluciohermano.validation.NotEmptyList;

public class NotEmptyListValidator
implements ConstraintValidator<NotEmptyList, List> {

@Override
public boolean isValid(List list,
                   ConstraintValidatorContext constraintValidatorContext) {
return list != null && !list.isEmpty();
}

@Override
public void initialize( NotEmptyList constraintAnnotation ) {
}
}

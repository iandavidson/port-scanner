package com.ian.davidson.port.scanner.validation;

import jakarta.validation.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class IpAddressValidator extends AbstractValidator<String> {

    private static final Pattern ADDRESS_PATTERN =
            Pattern.compile("^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$");

    @Override
    public String isValid(final String addr) {
        Matcher matcher = ADDRESS_PATTERN.matcher(addr);

        if (matcher.find()) {
            return addr;
        }else{
            throw new ValidationException("Address provided: " + addr + ", does not follow IP convention");
        }
    }
}

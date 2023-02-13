package io.github.liuruinian.phone.autoconfigure;

import io.github.liuruinian.phone.endpoint.PhoneProtectionEndpoint;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * @author liuruinian
 * @since 2023-02-08
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PhoneProtectionEndpoint.class)
public @interface EnableAliPhoneEndpoint {
}

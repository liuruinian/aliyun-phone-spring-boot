package io.github.liuruinian.phone.autoconfigure;

import io.github.liuruinian.phone.endpoint.AliPhoneEndpoint;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * @author liuruinian
 * @since 2023-02-08
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AliPhoneEndpoint.class)
public @interface EnableAliPhoneEndpoint {
}

package io.nayasis.basica.spring.config.error;

import io.nayasis.basica.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Error handler
 *
 * @author nayasis@gmail.com
 * @since 2016-03-04
 */
@Component
@Slf4j
public class ErrorHandler {

    @Value( "${server.error.include-exception:false}" )
    private boolean includeException;

    @Autowired
    private ThrowableHandler throwHandler;

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String,Object> getErrorAttributes( WebRequest request, boolean includeStackTrace ) {

                Map<String,Object> attributes = super.getErrorAttributes( request, includeStackTrace );
                Throwable error = getError( request );

                if( error != null ) {

                    throwHandler.logError( error );

                    if( includeException  ) attributes.put( "exception",   error.getClass().getName()   );
                    if( includeStackTrace ) attributes.put( "errorDetail", throwHandler.toString(error) );

//                    if( error instanceof BizException ) {
//                        attributes.put( "message", Strings.nvl( error.getMessage() ) );
//                        attributes.put( "errorCd", ( (BizException) error ).errorCode() );
//                    } else {
                        attributes.put( "message", error.getMessage() );
                        attributes.put( "errorCd", Strings.nvl(attributes.get("status")) );
//                    }

                }

                return attributes;

            }
        };
    }

}

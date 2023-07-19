package io.github.janekkodowanie.ezML;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order
class LoggerFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest httpRequest) {
            logger.info("[doFilter] " + httpRequest.getMethod() + " " + httpRequest.getRequestURL());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}

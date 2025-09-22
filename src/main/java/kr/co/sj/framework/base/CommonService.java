package kr.co.sj.framework.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;

public abstract class CommonService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private void setResponseHeader(HttpServletResponse response) {
        //response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-XSS-Protection", "1");
    }
}

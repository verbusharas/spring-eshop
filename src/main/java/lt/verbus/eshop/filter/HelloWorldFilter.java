package lt.verbus.eshop.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class HelloWorldFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("++++++++++  Hello World! Execution Started! +++++++++");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        filterChain.doFilter(servletRequest, servletResponse);
        stopWatch.stop();
        System.out.println("--------- " + servletRequest.toString());
        System.out.println("----------- Execution ended! " + stopWatch.getTotalTimeMillis() + " ms -----------------");
    }
}

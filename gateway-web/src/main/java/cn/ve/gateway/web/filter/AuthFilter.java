package cn.ve.gateway.web.filter;

import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ve
 * @date 2020/4/15 16:28
 */
public class AuthFilter implements HttpServerFilter {

    public final static Set<String> PUBLIC_SERVER = new HashSet<>();


    @Override
    public int getOrder() { // 序号,越小越优先
        return 0;
    }

    @Override
    public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx) {
        String microServiceName = invocation.getMicroserviceName();
        if (!PUBLIC_SERVER.contains(microServiceName)) {
            String token = httpServletRequestEx.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isEmpty(token)) {// 检查权限
                return Response.failResp(new InvocationException(javax.ws.rs.core.Response.Status.UNAUTHORIZED, "unauthorized"));

            }
        }
        return null;
    }
}

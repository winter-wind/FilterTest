package cc.biibi.filterdemo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterTest extends HttpFilter {
       
   
	private static final long serialVersionUID = -2307222528253133759L;
	
	//从web.xml中获取userSessionKey，rediretPage，uncheckedUrls
	private String userSessionKey;//session键值对
	private String rediretPage;//若未登录，重定向到登录页面
	private String uncheckedUrls;//不需要拦截的页面
	
	@Override
	public void init() throws ServletException {
		//获取web.xml中的信息
		ServletContext servletContext = getFilterConfig().getServletContext();
		userSessionKey = servletContext.getInitParameter("userSessionKey");
		rediretPage = servletContext.getInitParameter("rediretPage");
		uncheckedUrls = servletContext.getInitParameter("uncheckedUrls");
	}

	@Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    		throws IOException, ServletException {
		
		String servletPath = request.getServletPath();//获取访问页面的url
		
		//检查被访问的页面是否是不拦截页面
		List<String> urlList = Arrays.asList(uncheckedUrls.split(","));//不拦截页面有多个用“,”分割
		if(urlList.contains(servletPath)){//List集合中是否包含有String
			chain.doFilter(request, response);//如果有放行，允许访问
			return;
		}
		
		//从session中获取key对应的值，若值不存在，则重定向到rediretPage
		Object user = request.getSession().getAttribute(userSessionKey);
														//注意此处是一个变量不是一个setAttribute（"",""）中的值
		if(user == null){
			System.out.println("user:"+user);
			response.sendRedirect(request.getContextPath()+rediretPage);
										//url前缀+后缀
			//return;
		}
			
		
		//若存在，则放行，允许访问
    	chain.doFilter(request, response);
    }
}

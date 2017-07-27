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
	
	//��web.xml�л�ȡuserSessionKey��rediretPage��uncheckedUrls
	private String userSessionKey;//session��ֵ��
	private String rediretPage;//��δ��¼���ض��򵽵�¼ҳ��
	private String uncheckedUrls;//����Ҫ���ص�ҳ��
	
	@Override
	public void init() throws ServletException {
		//��ȡweb.xml�е���Ϣ
		ServletContext servletContext = getFilterConfig().getServletContext();
		userSessionKey = servletContext.getInitParameter("userSessionKey");
		rediretPage = servletContext.getInitParameter("rediretPage");
		uncheckedUrls = servletContext.getInitParameter("uncheckedUrls");
	}

	@Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    		throws IOException, ServletException {
		
		String servletPath = request.getServletPath();//��ȡ����ҳ���url
		
		//��鱻���ʵ�ҳ���Ƿ��ǲ�����ҳ��
		List<String> urlList = Arrays.asList(uncheckedUrls.split(","));//������ҳ���ж���á�,���ָ�
		if(urlList.contains(servletPath)){//List�������Ƿ������String
			chain.doFilter(request, response);//����з��У��������
			return;
		}
		
		//��session�л�ȡkey��Ӧ��ֵ����ֵ�����ڣ����ض���rediretPage
		Object user = request.getSession().getAttribute(userSessionKey);
														//ע��˴���һ����������һ��setAttribute��"",""���е�ֵ
		if(user == null){
			System.out.println("user:"+user);
			response.sendRedirect(request.getContextPath()+rediretPage);
										//urlǰ׺+��׺
			//return;
		}
			
		
		//�����ڣ�����У��������
    	chain.doFilter(request, response);
    }
}

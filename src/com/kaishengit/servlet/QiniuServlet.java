package com.kaishengit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

@WebServlet("/qiniu")
public class QiniuServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String AK = "Nz18RXVC1ZLhbrMCj0nVGT3ScE_9h27-EGmVwiAf";
		String SK = "58VAodtrPhF9667Ajh8FGoDouKsUHGX0VoNTVb0J";
		String bucketName = "rain129";
		
		Auth auth = Auth.create(AK, SK);

		//计算上传的token
		StringMap map = new StringMap();
		map.put("returnUrl", "http://localhost:80/qiniucallback");
		
		String token = auth.uploadToken(bucketName,null,3600,map);
		
		req.setAttribute("token", token);
		req.getRequestDispatcher("qiniu.jsp").forward(req, resp);
	}

}

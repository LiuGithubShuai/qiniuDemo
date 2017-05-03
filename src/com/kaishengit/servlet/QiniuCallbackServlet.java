package com.kaishengit.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.Gson;

@WebServlet("/qiniucallback")
public class QiniuCallbackServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uploadRet = req.getParameter("upload_ret");
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		
		if (StringUtils.isEmpty(uploadRet)) {
			resp.sendError(404);
		} else {
			// Base64解密
			String result = new String(Base64.decodeBase64(uploadRet));

			Map<String, Object> map = new Gson().fromJson(result, HashMap.class);

			// 这里的fileName是存储时的名字
			String fileName = (String) map.get("key");
			
			req.setAttribute("fileName", fileName);
			resp.getWriter().write("保存成功");
			req.getRequestDispatcher("qiniu.jsp").forward(req, resp);
			
		}
	}

}

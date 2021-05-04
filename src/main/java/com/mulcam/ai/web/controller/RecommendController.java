package com.mulcam.ai.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.ai.web.service.RecommendService;
import com.mulcam.ai.web.vo.OrderVO;
import com.mulcam.ai.web.vo.RecommendVO;



@Controller
public class RecommendController {
	
	private PythonInterpreter interpreter;
	
	@Autowired 
	RecommendService recommendService;
	
	@RequestMapping(value = "recommend.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=UTF-8")			
	
	@ResponseBody
	public String recommend(HttpServletRequest request, HttpServletResponse response){
		String title = request.getParameter("title");
		String category = recommendService.findCategory(title);
		String sb = "";
		RecommendVO recommendVO;
		String list = "";
		
        try {
			URL url = new URL("http://127.0.0.1:8000/recommend/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; utf-8"); //json형식으로 전송, Request body를 JSON으로 던져줌
			conn.setRequestProperty("Accept", "application/json");	// Request data를 JSON으로 받도록 설정
			conn.setDoOutput(true);	// Output Stream을 POST 데이터로 전송
			String jsonInputString = "{\"title\":\""+title+"\",\"category\":\""+category+"\"}";

			//JSON 보내는 Output Stream
			try(OutputStream os = conn.getOutputStream()){
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input,0,input.length);
			}
			
			// Response data 받는 부분
			try(BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), "UTF-8"))){
				String responseLine = null;
				while((responseLine = br.readLine()) != null) {
					sb = responseLine;
				}
				br.close();
			}
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        sb = sb.replaceAll("\\\"","");
        sb = sb.replaceAll("\\\\","\"");
        JSONObject o=new JSONObject(sb);       
        JSONObject ISBN=o.getJSONObject("ISBN");
        System.out.println(ISBN);
        for (int i=0;i<ISBN.length();i++) {
        	String num = Integer.toString(i);
        	String isbn = String.valueOf(ISBN.get(num));
        	recommendVO = recommendService.recommendBook(isbn);
        	list = list +  recommendVO.toString() + "\n";
        }
        
       System.out.println(list);
       return list;
}
}

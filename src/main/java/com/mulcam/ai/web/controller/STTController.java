package com.mulcam.ai.web.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class STTController {

	@RequestMapping(value = "stt.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody	
	public static String main(String[] args) {
		String response="";
		try {
			AudioFormat format=new AudioFormat(16000,8,2,true,true);
			DataLine.Info info=new DataLine.Info(TargetDataLine.class, format);
			if(!AudioSystem.isLineSupported(info)) {
				System.out.println("Line is not supported");
			}
			
			final TargetDataLine targetDataLine=(TargetDataLine)AudioSystem.getLine(info);
			targetDataLine.open();
			System.out.println("할 말 있으면 해보세요. (제한시간 4초)");
			targetDataLine.start();
			Thread stopper=new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
		    		File newdir = new File("C:/Users/Public/Pictures/Shotting-face");
		    		newdir.mkdir();
					
					AudioInputStream audioStream=new AudioInputStream(targetDataLine);
					File wavFile=new File("C:/Users/Public/Pictures/Shotting-face/Audio-user.wav");
					try {
						AudioSystem.write(audioStream,  AudioFileFormat.Type.WAVE, wavFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			stopper.start();
			Thread.sleep(4000);
			targetDataLine.stop();
			
			
			////////////////////////////
			
			//String clientId = "4fl0k53shk";             // Application Client ID";
	        //String clientSecret = "tFSMyBfocPoGkM1vagG6rBuX1KEiS6ss3vGREN5b";     // Application Client Secret";
			String clientId ="cwbj4zqzi3";
			String clientSecret = "MWpEyxSE9MsMbEARKuCxEt8E2naInJwjpbm5zaR9";
	        try {       	
	            String imgFile = "C:/Users/Public/Pictures/Shotting-face/Audio-user.wav";
	            File voiceFile = new File(imgFile);

	            String language = "Kor";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
	            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
	            URL url = new URL(apiURL);

	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	            conn.setUseCaches(false);
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestProperty("Content-Type", "application/octet-stream");
	            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

	            OutputStream outputStream = conn.getOutputStream();
	            FileInputStream inputStream = new FileInputStream(voiceFile);
	            byte[] buffer = new byte[4096];
	            int bytesRead = -1;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.flush();
	            inputStream.close();
	            BufferedReader br = null;
	            int responseCode = conn.getResponseCode();
	            if(responseCode == 200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            } else {  // 오류 발생
	                System.out.println("error!!!!!!! responseCode= " + responseCode);
	                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            }
	            String inputLine;

	            if(br != null) {
	                while ((inputLine = br.readLine()) != null) {
	                	response += inputLine;
	                }
	                br.close();
	                System.out.println(response.toString());
	            } else {
	                System.out.println("error !!!");
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	    //내가 한 말만 뽑기 (파싱)
		System.out.println("response : " + response);
        JSONObject o=new JSONObject(response);
//        JSONArray bubbles=o.getJSONArray(response);
//        JSONObject bubbles0=bubbles.getJSONObject(0);
//        JSONObject data=bubbles0.getJSONObject("text");
//        String isay=(String) data.get("text");
        String isay = o.getString("text");
        System.out.println("--->"+isay);
        response = "";
        
        return isay;
	}

}
package com.mulcam.ai.web.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class TTSController {

    public static void main(String[] args) {
        String clientId = "cwbj4zqzi3";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "MWpEyxSE9MsMbEARKuCxEt8E2naInJwjpbm5zaR9";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("안녕하세요 저는 챗봇이에요.", "UTF-8"); // 13자
            String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            String postParams = "speaker=nsujin&volume=0&speed=0&pitch=0&format=wav&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 mp3 파일 생성 x -> 오디오 챗봇 쩜 엠피쓰리
	    		File newdir = new File("C:/Users/Public/Pictures/Shotting-face");
	    		newdir.mkdir();                
                File f = new File("C:/Users/Public/Pictures/Shotting-face/Audio-chatbot.wav");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                System.out.println("wav 생성이 완료되었습니다.");
                is.close();
            } else {  // 오류 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
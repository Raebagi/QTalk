package org.example.qtalk.Service;

import org.springframework.stereotype.Service;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@Service
public class DateService {

    public String getDateInfo() {
        StringBuilder sb = new StringBuilder();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B490007/qualExamSchd/getQualExamSchdList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("dataFormat", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*응답 데이터 형식*/
            urlBuilder.append("&" + URLEncoder.encode("implYy", "UTF-8") + "=" + URLEncoder.encode("2024", "UTF-8")); /*시행년도*/
            urlBuilder.append("&" + URLEncoder.encode("qualgbCd", "UTF-8") + "=" + URLEncoder.encode("T", "UTF-8")); /*자격구분코드*/
            urlBuilder.append("&" + URLEncoder.encode("jmCd", "UTF-8") + "=" + URLEncoder.encode("7910", "UTF-8")); /*종목코드 값*/

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // StringBuilder에 저장된 내용을 문자열로 반환
        return sb.toString();
    }
}

package org.example.qtalk.Service;

import org.example.qtalk.Entity.QualificationEntity;
import org.example.qtalk.repository.QualificationRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;

@Service
public class QualificationService {

    private final QualificationRepository qualificationRepository;

    public QualificationService(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    //한국산업인력공단 api - 국가자격 종목
    public String saveQualificationInfo() {
        StringBuilder sb = new StringBuilder();
        String result = null;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.q-net.or.kr/api/service/rest/InquiryListNationalQualifcationSVC/getList");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=");
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");
            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if (responseCode >= 200 && responseCode <= 300) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                conn.disconnect();

                // XML 파싱
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(sb.toString())));

                document.getDocumentElement().normalize();
                NodeList itemsList = document.getElementsByTagName("item");

                for (int i = 0; i < itemsList.getLength(); i++) {
                    Node node = itemsList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element itemElement = (Element) node;

                        QualificationEntity entity = new QualificationEntity();
                        entity.setId(Integer.parseInt(getTagValue("jmcd", itemElement)));
                        entity.setName(getTagValue("jmfldnm", itemElement));
                        entity.setType(getTagValue("qualgbcd", itemElement));

                        qualificationRepository.save(entity);
                    }
                }
                result = "ok";
            } else {
                result = "false";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
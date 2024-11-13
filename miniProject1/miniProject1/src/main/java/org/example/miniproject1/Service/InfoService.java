package org.example.miniproject1.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InfoService {

    public String fetchResearchPapers(String domain) {
        String arxivUrl = "http://export.arxiv.org/api/query?search_query=all:" + domain + "&start=0&max_results=5";
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(arxivUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                int charCount = 0;
                while ((inputLine = in.readLine()) != null && charCount < 3000) {
                    response.append(inputLine);
                    charCount += inputLine.length();
                }
                in.close();
            } else {
                System.out.println("GET request failed for research papers. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }


    public void printResearchPapers(String xmlResponse) {
        String[] papers = xmlResponse.split("<entry>");
        System.out.println("List of Research Papers:");
        for (String paper : papers) {
            if (paper.contains("<title>")) {
                String title = extractTagContent(paper, "<title>", "</title>");
                String summary = extractTagContent(paper, "<summary>", "</summary>");
                if (title != null) {
                    System.out.println("- " + title);
                }
                if (summary != null) {
                    System.out.println("  Summary: " + summary + "\n");
                }
            }
        }
    }



    private String extractTagContent(String content, String startTag, String endTag) {
        try {
            int start = content.indexOf(startTag) + startTag.length();
            int end = content.indexOf(endTag, start);
            return content.substring(start, end).trim();
        } catch (Exception e) {
            return null;
        }
    }
}

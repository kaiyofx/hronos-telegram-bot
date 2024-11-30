package ru.kaiyo.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStream;

@Getter
public class Requests {

    private JsonObject response;

    public String getAnswer() {
        return response.get("answer").getAsString();
    }

    @SneakyThrows
    public Requests post(String url, JsonObject object) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(object.toString());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(params);
            CloseableHttpResponse response = client.execute(request);
            if (response != null) {
                try(InputStream in = response.getEntity().getContent()) {
                    this.response = JsonParser.parseString(new String(in.readAllBytes())).getAsJsonObject();
                }
            }
        }
        return this;
    }

}

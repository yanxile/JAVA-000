import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

/**
 * @ClassName HttpClientDemo
 * @Description
 * @Author yc
 * @Date 2020/10/28 22:43
 */
public class HttpClientDemo {
	public static void main(String[] args) {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet("http://localhost:8801");

			try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
				System.out.println(response.getCode() + " " + response.getReasonPhrase());
				HttpEntity entity1 = response.getEntity();
				EntityUtils.consume(entity1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
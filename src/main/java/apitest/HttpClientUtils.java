package apitest;

import java.io.IOException;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.StringUtil;

public class HttpClientUtils {
	public static boolean openProxy = true;   //暂时关闭代理模式
	// 代理对象
	static HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
	//HttpClient中可设置三个超时：RequestTimeout（连接池获取到连接的超时时间）、ConnectTimeout（建立连接的超时）、SocketTimeout（获取数据的超时时间）,也可以设置代理配置
	static RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build(); //
//	// http连接池管理
	static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	// 第四版本扩展了json token
	public static String doPostJson(String url, String params, Map<String, Object> headers) {
		System.out.println("postJson headers: "+headers);
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			if (headers != null && !headers.isEmpty()) {
				Set<String> headerSet = headers.keySet();
				for (String header : headerSet) {
					System.out.println(header);
					System.out.println(headers.get(header).toString());
					httpPost.addHeader(header, headers.get(header).toString());
				}
			}
			if (!isEmpty(params)) {
				StringEntity stringEntity = new StringEntity(params, "utf-8");
				stringEntity.setContentEncoding("UTF-8");
//				stringEntity.setContentType("application/json");
				httpPost.setHeader("Content-type", "application/json");

				httpPost.setEntity(stringEntity);
			}
			if (openProxy) {
				httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
			}
			response = httpClient.execute(httpPost);
			// 返回状态
			StatusLine line = response.getStatusLine();
			if (line.getStatusCode() == 200) {
				// 返回页面信息
				HttpEntity httpEntity = response.getEntity();
				return EntityUtils.toString(httpEntity, "utf-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public static String doPost(String url, Map<String, Object> params) {
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				Set<String> keySet = params.keySet();
				for (String key : keySet) {
					list.add(new BasicNameValuePair(key, params.get(key).toString()));
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
				httpPost.setEntity(urlEncodedFormEntity);
			}
			if (openProxy) {
				httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
			}
			response = httpClient.execute(httpPost);
			// 返回状态
			StatusLine line = response.getStatusLine();
			if (line.getStatusCode() == 200) {
				// 返回页面信息
				HttpEntity httpEntity = response.getEntity();
				return EntityUtils.toString(httpEntity, "utf-8");
			}
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			params.clear();
		}
		return "";

	}

	public static String doPost(String url, String params) {
		// httpPost
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			// List<NameValuePair> list = new ArrayList<NameValuePair>();
//			list.add(new BasicNameValuePair("method", "loginMobile"));
//			list.add(new BasicNameValuePair("loginname", "abc"));
//			list.add(new BasicNameValuePair("loginpass", "abc"));
			// "method=loginMobile&loginname=test1&loginpass=test1&testloginpass=您好"
			if (!isEmpty(params)) {
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				String[] pStrings = params.split("&");
				for (int i = 0; i < pStrings.length; i++) {
					String param = pStrings[i];
					String[] param_array = param.split("=");
					list.add(new BasicNameValuePair(param_array[0], param_array[1]));
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
				httpPost.setEntity(urlEncodedFormEntity);
			}
			if (openProxy) {
				httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
			}
			response = httpClient.execute(httpPost);
			// 返回状态
			StatusLine line = response.getStatusLine();
			// 返回页面信息
			HttpEntity httpEntity = response.getEntity();
			return EntityUtils.toString(httpEntity, "utf-8");
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public static String doGet(String url) {
		// 创建默认的http连接池
		HttpGet get = new HttpGet(url);
		System.out.println("Requesting URL: " + url);

		try {
			if (openProxy) {
                System.out.println("set up proxy");
				httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
			}
			CloseableHttpResponse response = httpClient.execute(get);
			// 返回状态
			StatusLine line = response.getStatusLine();
			if (line.getStatusCode() == 200) {
				// 返回页面信息
				HttpEntity httpEntity = response.getEntity();
				return EntityUtils.toString(httpEntity, "utf-8");
			}
		} catch (IOException e) {
			 e.printStackTrace();
            System.out.println("get请求失败");
		}
		return "";
	}

	public static void main(String[] args) {
		openProxy = false;
        String resp = doGet("http://118.24.13.38:8080/goods/UserServlet?method=loginMobile&loginname=test1&loginpass=test1");
		Map<String, Object> headers = new LinkedHashMap<String, Object>();
		headers.put("token","hZ3VGKtTKuw2KVQNTKQ2v96yLuQrf5/L/B0T+5F8tVrviF95BPSfcoaCoWtnuyOb1pRuEvMjFJRQVm5H4JcrHNoE74IumpBgWSDBSYiII1my3RUj4UU9AZd4Y1G8QzPtBENj5nV3VY0okqz07a3FJg==");
		String resp2=doPostJson("https://www.vandream.com:9090/api/mallCartService/addCart?","{\"data\":\"eTkZbbR3BJJ/r+GFRqUBJRmW7oQZ103PaYybGS6OXOqAbQ7MqHiuHlrsitK5cNOAmOb2jnqokmpVh1rckpbzPErN4jeqnIK17k26xpXaFZZoB41qovJoc9KvBGpicfaM/EINGcme2E8kfbynrVdk7EuCfoIITmbSf63kufaTbiA==\",\"tk\":\"dd7e37d9f3287cd89ccb736c69074e27\"}",headers);
		System.out.println(resp2);
        // doPost("http://118.24.13.38:8080/goods/UserServlet",
		// "method=loginMobile&loginname=test1&loginpass=test1");

//		Map<String, Object> paMap = new HashMap<String, Object>();
//		paMap.put("method", "loginMobile");
//		paMap.put("loginname", "abc");
//		paMap.put("loginpass", "abc");
//		paMap.put("test", "123123");
//		doPost("http://118.24.13.38:8080/goods/UserServlet", paMap);
	}

}

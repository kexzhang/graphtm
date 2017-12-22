/**
 * 南部战区 联合作战指挥信息系统
 * @version 2.0
 * @copyright 北京华如科技股份有限公司 
 */
package com.hrtek.tool;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @description 
 * @author zkx
 * 2017年2月22日 上午10:38:52
 */
public class RestHttpClient {
	/*
	 * url传参地址
	 * encode：编码，默认请设置为UTF-8；
	 * map post传递内容
	 */
	private static CloseableHttpClient client=HttpClients.createDefault();
	private static RequestConfig config=RequestConfig.custom().setSocketTimeout(8000).build();
public static  String restresponse(String url,String encode,String params) throws ClientProtocolException, IOException{
	//定义返回值
	String body="";
	 //创建httpClient对象
	//post请求
	HttpPost httpPost=new HttpPost(url);
	//HttpClientContext context=new HttpClientContext();
	//context.getRequest();
	//设置超时时间
	httpPost.setConfig(config);
	//头部信息设置
	httpPost.setHeader("Content-type","application/x-www-form-urlencoded");
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("User-Agent","Mozilla/5.0(compatible;MSIE5.0;Windows NT;DigExt)");
	//设置参数到具体请求中
	StringEntity stringEntity=new StringEntity(params, Charset.forName(encode));
	stringEntity.setContentType("application/json");
	httpPost.setEntity(stringEntity);

	CloseableHttpResponse response=client.execute(httpPost);
	//获得反馈结果
	HttpEntity entity=response.getEntity();
	if(entity!=null){
		body=EntityUtils.toString(entity, encode);
	}
	EntityUtils.consume(entity);
	//释放连接
	response.close();
	return body;
	
}



/*
 * get数据内容
 * encode：编码，默认请设置为UTF-8；
 * 
 */
public static  String restrequest(String url,String encode) throws ClientProtocolException, IOException{
//定义返回值
String body="";
 //创建httpClient对象

//post请求
HttpGet httpGet=new HttpGet(url);
//设置请求和传输超时时间
httpGet.setConfig(config);
//设置参数到具体请求中
//头部信息设置
httpGet.setHeader("Content-type","application/x-www-form-urlencoded");
httpGet.setHeader("Accept", "application/json");
httpGet.setHeader("User-Agent","Mozilla/5.0(compatible;MSIE5.0;Windows NT;DigExt)");
//释放连接
CloseableHttpResponse response=client.execute(httpGet);
//获得反馈结果
	HttpEntity entity=response.getEntity();
	if(entity!=null){
		body=EntityUtils.toString(entity, encode);
	}
	EntityUtils.consume(entity);
	//释放连接
	response.close();
return body;

}
}

package com.yj.reservation.common.util;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 阿里云短信工具类
 * aliyun-java-sdk-core.jar
 */
public class AliSmsUtil {
	
	private static final String tpl = "SMS_467145174";
	// 短信签名
	private static final String sign = "海贝";

	// 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static final String accessKeyId = "LTAI5tPL2setaNqSWK9w3gg8";
	private static final String accessKeySecret = "4tqO4Cu4DofGjcHXnVmqEijKPM4LOs";
	
	public static void main(String[] args) throws Exception {

		sendCode("18661830391", "6666");

	}


	
	/**
	 * 发送验证码
	 * 
	 * @param phone 手机号
	 * @param code 验证码
	 */
	public static void sendCode(String phone, String code) throws Exception {

		StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
				.accessKeyId(accessKeyId)
				.accessKeySecret(accessKeySecret)
				.build());


		AsyncClient client = AsyncClient.builder()

				.credentialsProvider(provider)

				.overrideConfiguration(
						ClientOverrideConfiguration.create()
								.setEndpointOverride("dysmsapi.aliyuncs.com")

				)
				.build();

		SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
				.phoneNumbers(phone)
				.signName(sign)
				.templateCode(tpl)
				.templateParam("{\"code\":\"" + code + "\"}")
				.build();


		CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);

		SendSmsResponse resp = response.get();
		System.out.println(new Gson().toJson(resp));

		client.close();
	}

}
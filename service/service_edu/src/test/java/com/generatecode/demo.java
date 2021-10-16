package com.generatecode;

import ch.qos.logback.core.net.server.Client;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class demo {
    public static void main(String[] args) {
        DefaultProfile profile =
                DefaultProfile.getProfile("cn-shanghai", "替换你的密钥", "HnjJjtpysSXtIFZZ14DFS7gNXOwVqj");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("vod.cn-shanghai.aliyuncs.com");
        request.setSysVersion("2017-03-21");
        request.setSysAction("GetMezzanineInfo");
        request.putQueryParameter("AuthTimeout", "3600");
        request.putQueryParameter("VideoId", "14c949115ab02405aadb7f4c722823e92");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

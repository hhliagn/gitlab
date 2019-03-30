package com.cvte.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.cvte.base.pojo.Version;
import com.cvte.base.service.gitlabService;
import com.cvte.base.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class gitlabServiceImpl implements gitlabService {

    @Value("${host}")
    private String host; //仓库地址
    private HttpClientUtils httpClientUtils=new HttpClientUtils(false);

    @Override
    public int fetchProjectId(String repoName) {

        String URL=host+"/projects";
        Map<String, String> params = new HashMap<>();
        params.put("access_token","61a024f79e5f8ae39aad9cb7c5c6050ce011fe8aae365f4e4610fc57d97ce5c9");
        params.put("search",repoName);

        String content=httpClientUtils.sendGet(URL, params);
        content=content.substring(1,content.length()-1);
        Map<String,Object> resMap= JSON.parseObject(content,Map.class);

        int id = (int)resMap.get("id");
        return id;
    }

    @Override
    public String fetchCommitList(String repoName,Version version) {

        String branch = version.getBranch();
        String commit = version.getCommit();

        String URL = host+"/projects/:id/repository/commits/:sha/diff";

        if (URL.contains(":id")){

            int projectId = fetchProjectId(repoName);
            URL=URL.replace(":id",projectId+"");
        }

        URL=URL.replace(":sha",commit);

        Map<String, String> params = new HashMap<>();
        params.put("access_token","61a024f79e5f8ae39aad9cb7c5c6050ce011fe8aae365f4e4610fc57d97ce5c9");

        String content=httpClientUtils.sendGet(URL,params);
        content=content.substring(1,content.length()-1);

        Map<String,Object> resMap= JSON.parseObject(content, Map.class);
        String diff = (String) resMap.get("diff");

        return diff;
    }
}

package com.cvte.base.service;

import com.cvte.base.pojo.Version;

public interface gitlabService {

    //void setRepoName(String repoName);

    int fetchProjectId(String repoName);

    String fetchCommitList(String repoName,Version version);
}

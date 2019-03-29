package com.cvte.base.service.impl;

import com.cvte.base.pojo.Version;
import com.cvte.base.service.compareService;
import com.cvte.base.service.gitlabService;
import com.cvte.base.utils.StringListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class compareServiceImpl implements compareService {

    @Autowired
    private gitlabService gitlabService;

    @Override
    public String compareTwoVersion(String repoName, Version oldVersion, Version newVersion) {

        gitlabService.setRepoName(repoName);

        String oldVersionList = gitlabService.fetchCommitList(oldVersion);
        String newVersionList = gitlabService.fetchCommitList(newVersion);

        String diff = StringListUtils.compareCommitList(oldVersionList, newVersionList);

        return diff;
    }
}

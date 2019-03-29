package com.cvte.base.service;

import com.cvte.base.pojo.Version;

public interface compareService {

    String compareTwoVersion(String repoName,
                             Version oldVersion,
                             Version newVersion);
}

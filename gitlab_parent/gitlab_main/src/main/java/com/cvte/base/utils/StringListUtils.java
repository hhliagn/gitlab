package com.cvte.base.utils;

public class StringListUtils {

    public static String compareCommitList(String diff1,String diff2) {

        String[] diffList1 = diff1.split("\\n");
        String[] diffList2 = diff2.split("\\n");

        StringBuilder sb=new StringBuilder();

        int shortLength=diffList1.length;
        int dist=diffList2.length-diffList1.length;
        for (int i=0;i<dist;i++){
            sb.append(diffList2[i + shortLength]+" ");
        }
        return sb.toString();
    }
}

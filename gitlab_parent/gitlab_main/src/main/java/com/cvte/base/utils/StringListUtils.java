package com.cvte.base.utils;

import org.apache.logging.log4j.util.PropertySource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StringListUtils {

    //归并算法提取两个字符串差异数据
    public static String compareCommitList(String diff1,String diff2) throws Exception {

        String[] diffList1 = diff1.split("\\n");
        String[] diffList2 = diff2.split("\\n");

        String[] combine=new String[diffList1.length+diffList2.length];
        for (int i=0;i<diffList1.length;i++){
            combine[i]=diffList1[i];
        }
        int temp=diffList1.length;
        for (int i=0;i<diffList2.length;i++){
            combine[temp+i]=diffList2[i];
        }

        List<String> a=new ArrayList<>();
        List<String> b=new ArrayList<>();

        int p=0;
        int q=temp;
        while (p<temp||q<combine.length){

            if (p>=temp){
                b.add(combine[q]);
                q++;
            }
            if (q>=combine.length){
                a.add(combine[p]);
                p++;
            }

            int cmp = combine[p].compareTo(combine[q]);
            if (cmp!=0){
                a.add(combine[p]);
                b.add(combine[q]);
            }
            p++;
            q++;

            /*if (cmp>0){
                b.add(combine[q+1]);
                q++;
            }*/
        }

        File file = new File("diff.txt");
        FileOutputStream fos = new FileOutputStream(file);

        fos.write("commit1:".getBytes());
        for (String s : a) {

            fos.write(s.getBytes());
            fos.write("  ".getBytes());
        }

        fos.write(";".getBytes());

        fos.write("commit2:".getBytes());
        for (String s : b) {

            fos.write(s.getBytes());
            fos.write("  ".getBytes());
        }

        fos.flush();
        fos.close();
        return "OK";
    }





    /*public static String compareCommitList(String diff1,String diff2) {

        String[] diffList1 = diff1.split("\\n");
        String[] diffList2 = diff2.split("\\n");

        StringBuilder sb=new StringBuilder();

        //master 不往前走
        *//*int shortLength=diffList1.length;
        int dist=diffList2.length-diffList1.length;
        for (int i=0;i<dist;i++){
            sb.append(diffList2[i + shortLength]+" ");
        }
        return sb.toString();*//*

        //master 一直更新

        int shortLength = 0;
        int longLength = 0;

        String[] shortList=null;
        String[] longList=null;

        if (diffList1.length>diffList2.length){

            shortLength=diffList2.length;
            longLength=diffList1.length;

            shortList=diffList2;
            longList=diffList1;
        }else {

            shortLength = diffList1.length;
            longLength = diffList2.length;

            shortList=diffList1;
            longList=diffList2;
        }

        int temp=0;
        for (int i=0;i<shortLength;i++){
            if (!shortList[i].equals(longList[i])){

                temp=i;
                break;
            }
        }

        sb.append("diff:");
        sb.append("commit1:");
        for (int i=temp;i<shortLength;i++){

            if (i!=shortLength-1){
                sb.append(shortList[i]+"  ");
            }else {
                sb.append(shortList[i]);
            }
        }

        sb.append(" ; ");

        sb.append("commit2:");
        for (int i=temp;i<shortLength;i++){

            if (i!=shortLength-1){
                sb.append(longList[i]+"  ");
            }else {
                sb.append(longList[i]);
            }


        }

        if (shortLength!=longLength){

            sb.append("added:");
            sb.append("commit2:");
            for (int i=shortLength;i<longLength;i++){

                if (i!=longLength-1){
                    sb.append(longList[i]+"  ");
                }else {
                    sb.append(longList[i]);
                }
            }
        }

        String diff = sb.toString().replaceAll("\\\\ No newline at end of file", "");
        return diff;
    }*/
}

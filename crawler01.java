package com.my.package01;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class crawler01 {
    /**以一个导航网站为起始做一个遍历web的爬虫
     建立两个数组列表，一个用来存储待遍历的网址，另一个用来统计已遍历的网址
     没有使用任何框架的一个简单的小程序，所以程序会出现两个状况
     */
    public static void main(String [] args){
        try (java.util.Scanner input = new java.util.Scanner(System.in)) {
            int k=1;
            while(k==1) {
                System.out.print("Enter a URL");
                String url=input.nextLine();
                k=2;
                try{
                 java.net.URL checkurl=new java .net.URL(url) ;
                 java.util.Scanner checkinput=new java.util.Scanner(checkurl.openStream());
                 checkinput.close();
                 crawler(url);
            }
                catch(Exception awrong){
                k=1;
                System.out.println("");
                System.out.println("The URL is invalid,please write again");
                }
        }

    }

    }

    public static void crawler(String url) {
        ArrayList<String> listOfUnchecked =new ArrayList<>();
        ArrayList<String> listOfChecked =new ArrayList<>();
        listOfUnchecked.add(url);
        while(!listOfUnchecked .isEmpty()&&listOfChecked .size()<=300){
        String urlString=listOfUnchecked .remove(0);
        if(!listOfChecked.contains(urlString)) {
            listOfChecked.add(urlString);
            System.out.println("Crawl:"+urlString);
            for(String s:getMoreUrls(urlString)){
                if(!listOfChecked.contains(s))listOfUnchecked.add(s);
            }
        }
        }
        System.out.println("crawl is over");
        try {
            java.io.File filetest = new java.io.File("d:\\crawler01.txt");
            java.io.PrintWriter output = new java.io.PrintWriter(filetest);
            for(String s:listOfChecked){
                output.println(s);
                output.println("");
            }
            output.close();
            System.out.print("Websites are written into the text.....total "+listOfChecked.size()+"websites");
        }
        catch(Exception duringwrite)  {
            System.out.println("writting wrong");
        }

    }
    private static ArrayList<String> getMoreUrls(String urlString){
        ArrayList<String> list = new ArrayList<>();
        try {
            java.net.URL url = new java.net.URL(urlString);
            Scanner input = new Scanner(url.openStream());
            int startIndex = 0;
            while (input.hasNext()) {
                String line = input.nextLine();
                startIndex = line.indexOf("http:", startIndex);
                while (startIndex > 0) {
                    int endIndex = line.indexOf("\"", startIndex);
                    if (endIndex > 0) {
                        list.add(line.substring(startIndex, endIndex));
                        startIndex = line.indexOf("http:", endIndex);
                    } else startIndex = -1;
                }
            }
        }    catch(Exception here){
            System.out.println(" Error but ok:" + here.getMessage());
        }
        return list;
    }}
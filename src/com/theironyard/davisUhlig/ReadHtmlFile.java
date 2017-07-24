package com.theironyard.davisUhlig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by duhlig on 7/23/17.
 */
public class ReadHtmlFile {
    String html;
    File htmlFile = new File("dashboard.html");
    public void readFile() {
        if(htmlFile.getName().endsWith(".html")){
            try{
                Scanner htmlScanner = new Scanner(htmlFile);
                List<String> htmlContents = new ArrayList<>();
                while (htmlScanner.hasNext()){
                    htmlContents.add(htmlScanner.nextLine());
                }
                String html[] = new String[htmlContents.size()];
                html = htmlContents.toArray(html);

                for (int i = 0; i < html.length; i++){
                    System.out.println(html[i]);
                }
            }
            catch (FileNotFoundException ex){
                System.out.println("Could not find file *" + html + "*");
                ex.printStackTrace();
            }


        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr8.object;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pham Ngoc Minh
 */
public class CrawlData {
    public List<String> getStringObject(String sentenceString) {
        List<String> listObject = new ArrayList<String>();
        String temp = "";
        String[] strings = sentenceString.split(" ");
        for(String string: strings) {
            
            if (checkUpperLetter(string)) {
                temp += string + " ";
                
            }
            else {
                listObject.add(temp);
                temp = "";
            }
            if (isValidDate(string)) {
                listObject.add(string);
            }
        }
        
        
        return listObject;
    }
    public boolean checkUpperLetter(String object) {
        char[] letters = object.toCharArray();
        return Character.isUpperCase(letters[0]);
    }
    public boolean checkLowerLetter(String object) {
        char[] letters = object.toCharArray();
        return Character.isLowerCase(letters[0]);
    }

    /**
     *
     * @param inDate
     * @return
     */
    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
          dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
          return false;
        }
        return true;
    }
    public static void main(String[] args) {
        CrawlData crawlData = new CrawlData();
        String sentence = "Tổng thống Obama, thủ tướng Nguyễn Xuân Phúc và tập đoàn Apple đã đến thăm Văn Miếu tại Hà Nội ngày 20/7/1917";
        for(String string: crawlData.getStringObject(sentence)) {
            String s = string.replaceAll(",", "");
            System.out.println(s);
        }
        
    }
}

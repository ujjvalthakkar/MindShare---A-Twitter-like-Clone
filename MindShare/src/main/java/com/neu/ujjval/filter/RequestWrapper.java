/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.ujjval.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author ujjva
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = sanitize(values[i]);
        }
        return encodedValues;
    }
    
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        
        if(parameter.equals("email") || parameter.equals("password") || parameter.equals("useremail")){
        	return sanitize(value,"");
        }
        return sanitize(value);
    }
    
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return sanitize(value);
    }

    public static String sanitize(String s) {
        if (s == null) {
            return null;
        }
        String temp = s;
        temp = temp.replaceAll("<[^>]*>", " ");
        temp = temp.replaceAll("[\\&;`'\\\\\\|\"*?%@#!~<>^\\(\\)\\[\\]\\{\\}\\$\\x00]", "");
        temp = temp.replaceAll("\n", " ").replace("\r", " ").replace("\t", " ");
        temp = temp.replaceAll("\\s+", " ").trim();
        return temp;
    }
    
    public static String sanitize(String s,String s1) {
        if (s == null) {
            return null;
        }
        String temp = s;
        temp = temp.replaceAll("<[^>]*>", " ");
        temp = temp.replaceAll("[\\&;`'\\\\\\|\"*?%#!~<>^\\(\\)\\[\\]\\{\\}\\$\\x00]", "");
        temp = temp.replaceAll("\n", " ").replace("\r", " ").replace("\t", " ");
        temp = temp.replaceAll("\\s+", " ").trim();
        return temp;
    } 
}

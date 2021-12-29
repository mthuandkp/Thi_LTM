/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import org.json.JSONObject;

/**
 *
 * @author mthuan
 */
public class Test {
    
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("name", "Thuan");
        obj.put("age", 18);
        
        System.out.println(obj.get("name"));
    }
}

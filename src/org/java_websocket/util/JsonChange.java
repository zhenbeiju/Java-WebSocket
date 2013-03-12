package org.java_websocket.util;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

public class JsonChange {

    public String text2json(String value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("", value);
        return "";
    }

    public static void text2json() throws Exception {
        JSONStringer stringer = new JSONStringer();
        String str = stringer.object().key("name").value("xiazdong").key("age").value(20).endObject().toString();
        System.out.println(str);
    }

    public static String getErrorStr(int errorCode) {
        JSONStringer stringer = new JSONStringer();
        String st = stringer.object().key(KeyList.METHODNAME).value(KeyList.ERROR_ID).key(KeyList.ERROR)
                .value(errorCode).endObject().toString();
        return st;
    }

    public static String getNormalMeg(String room, String msg) {
        
        return "";
    }

}

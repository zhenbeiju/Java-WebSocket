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
        JSONStringer stringer = new JSONStringer();
        String st = stringer.object().key(KeyList.METHODNAME).value(KeyList.NORMAL_MESSAGE_ID)
                .key(KeyList.NORMAL_MESSAGE).value(msg).key(KeyList.MESSAGE_ROOM).value(room).endObject().toString();
        return st;
    }

    public static String enterInRoom(String room) {
        JSONStringer stringer = new JSONStringer();
        String st = stringer.object().key(KeyList.METHODNAME).value(KeyList.ENTERIN_ID).key(KeyList.ENTERIN)
                .value(room).endObject().toString();
        return st;
    }
    
    public static String enterInRoom(String room,String nickname) {
        JSONStringer stringer = new JSONStringer();
        String str = stringer.object().key(KeyList.METHODNAME).value(KeyList.ENTERIN_ID).key(KeyList.MESSAGE_ROOM)
                .value(room).key(KeyList.SETUSERNICKNAME).value(nickname).endObject().toString();
        return str;
    }

    public static String quitOutRoom(String room) {
        JSONStringer stringer = new JSONStringer();
        String st = stringer.object().key(KeyList.METHODNAME).value(KeyList.QUITOUT_ID).key(KeyList.QUITOUT)
                .value(room).endObject().toString();
        return st;
    }

    public static String getUserList(String room) {
        JSONStringer stringer = new JSONStringer();
        String st = stringer.object().key(KeyList.METHODNAME).value(KeyList.GETUSERLIST_ID).key(KeyList.GETUSERLIST)
                .value(room).endObject().toString();
        return st;
    }

    public static String setClintID(String id) {
        JSONStringer stringer = new JSONStringer();
        String st = stringer.object().key(KeyList.METHODNAME).value(KeyList.SETUSERCLINTID_ID)
                .key(KeyList.SETUSERCLINTID).value(id).endObject().toString();
        return st;
    }

    public static String setUserNickName(String name) {
        JSONStringer stringer = new JSONStringer();
        String st = stringer.object().key(KeyList.METHODNAME).value(KeyList.SETUSERNICKNAME_ID)
                .key(KeyList.SETUSERNICKNAME).value(name).endObject().toString();
        return st;
    }

}

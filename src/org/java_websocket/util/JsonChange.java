package org.java_websocket.util;

import org.java_websocket.util.KeyList;

import org.json.*;
import org.json.me.JSONException;
import org.json.me.JSONStringer;

public class JsonChange {

    public static String getErrorStr(int errorCode) {
        JSONStringer stringer = new JSONStringer();
        String st = "";
        try {
            st = stringer.object().key(KeyList.METHODNAME).value(KeyList.ERROR_ID).key(KeyList.ERROR).value(errorCode)
                    .endObject().toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }

    public static String getNormalMeg(String room, String msg) {
        JSONStringer stringer = new JSONStringer();
        String st = "";
        try {
            st = stringer.object().key(KeyList.METHODNAME).value(KeyList.NORMAL_MESSAGE_ID).key(KeyList.NORMAL_MESSAGE)
                    .value(msg).key(KeyList.MESSAGE_ROOM).value(room).endObject().toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }

    public static String enterInRoom(String room) {
        JSONStringer stringer = new JSONStringer();
        String st = "";
        try {
            st = stringer.object().key(KeyList.METHODNAME).value(KeyList.ENTERIN_ID).key(KeyList.ENTERIN).value(room)
                    .endObject().toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }

    public static String enterInRoom(String room, String nickname) {
        JSONStringer stringer = new JSONStringer();
        String str = "";
        try {
            str = stringer.object().key(KeyList.METHODNAME).value(KeyList.ENTERIN_ID).key(KeyList.MESSAGE_ROOM)
                    .value(room).key(KeyList.SETUSERNICKNAME).value(nickname).endObject().toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    public static String quitOutRoom(String room,String nickName) {
        JSONStringer stringer = new JSONStringer();
        String st = "";
        try {
            st = stringer.object().key(KeyList.METHODNAME).value(KeyList.QUITOUT_ID).key(KeyList.QUITOUT).value(room)
                    .endObject().toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }

    public static String getUserList(String room) {
        JSONStringer stringer = new JSONStringer();
        String st = "";
        try {
            st = stringer.object().key(KeyList.METHODNAME).value(KeyList.GETUSERLIST_ID).key(KeyList.GETUSERLIST)
                    .value(room).endObject().toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }

    public static String setClintID(String id) {
        JSONStringer stringer = new JSONStringer();
        String st = "";
        try {
            st = stringer.object().key(KeyList.METHODNAME).value(KeyList.SETUSERCLINTID_ID).key(KeyList.SETUSERCLINTID)
                    .value(id).endObject().toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }

    public static String setUserNickName(String name) {
        JSONStringer stringer = new JSONStringer();
        String st = "";
        try {
            st = stringer.object().key(KeyList.METHODNAME).value(KeyList.SETUSERNICKNAME_ID)
                    .key(KeyList.SETUSERNICKNAME).value(name).endObject().toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }

}

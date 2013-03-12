package main.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.util.JsonChange;
import org.java_websocket.util.KeyList;

public class WebSocketManager {
    private WebSocketClient myWebSocket;
    private String myNickName;
    private HashMap<String, IRoomInterface> mRooms = new HashMap<String, IRoomInterface>();
    private HashMap<String, IRoomInterface> tempRooms = new HashMap<String, IRoomInterface>();
    public static WebSocketManager mSocketManager = new WebSocketManager();
    private boolean mConnectStatus = false;

    public void init(String name) {
        myNickName = name;
        try {
            myWebSocket = new WebSocketClient(new URI(""), myNickName) {

                @Override
                public void onClose(int arg0, String arg1, boolean arg2) {
                    // TODO Auto-generated method stub
                    mConnectStatus = false;
                }

                @Override
                public void onError(Exception arg0) {
                    // TODO Auto-generated method stub
                    mConnectStatus = false;
                }

                @Override
                public void onMessage(String arg0) {
                    // TODO Auto-generated method stub
                    parseMessage(arg0);
                }

                @Override
                public void onOpen(ServerHandshake arg0) {
                    // TODO Auto-generated method stub
                    mConnectStatus = true;
                }

            };
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void connect() {
        myWebSocket.connect();
    }

    public void close() {
        myWebSocket.close();
    }

    public void sendMsg(String room, String msg) {
        JSONStringer stringer = new JSONStringer();
        String str = stringer.object().key(KeyList.METHODNAME).value(KeyList.NORMAL_MESSAGE_ID)
                .key(KeyList.MESSAGE_ROOM).value(room).key(KeyList.NORMAL_MESSAGE).value(msg).endObject().toString();
        myWebSocket.send(str);
    }

    public void JoinRoom(IRoomInterface mInterface) {
        tempRooms.put(mInterface.RoomName, mInterface);
        myWebSocket.sendMessage(JsonChange.enterInRoom(mInterface.RoomName));
    }

    public void QuitRoom(IRoomInterface mInterface) {
        // TODO send msg to server to quit this room
        mRooms.remove(mInterface.RoomName);
        myWebSocket.sendMessage(JsonChange.quitOutRoom(mInterface.RoomName));
    }

    public void parseMessage(String msg) {
        JSONObject jsonObject = JSONObject.fromObject(msg);
        int methodname = jsonObject.getInt(KeyList.METHODNAME);
        String room = jsonObject.getString(KeyList.MESSAGE_ROOM);
        String nickName;
        switch (methodname) {
        case KeyList.ENTERIN_ID:

            nickName = jsonObject.getString(KeyList.SETUSERNICKNAME);
            if (nickName.equals(myNickName)) {
                // TODO reciver sb.self has enter in this room
                if(tempRooms.containsKey(room)){
                    mRooms.put(room, tempRooms.get(room));
                    tempRooms.remove(room);
                    mRooms.get(room).onSysMessage("you has join this room . you can speak now!");
                }
          
            } else {
                if (mRooms.containsKey(room)) {
                    mRooms.get(room).onSysMessage(nickName + "");
                } else {
                    // LogManager.e(" error room name:" + room);
                }
            }
            break;
        case KeyList.QUITOUT_ID:
            nickName = jsonObject.getString(KeyList.SETUSERNICKNAME);
            if (nickName.equals(myNickName)) {
                if (mRooms.containsKey(room)) {
                    mRooms.remove(room);
                } else {
                    // LogManager.e(" error room name:" + room);
                }
            } else {
                if (mRooms.containsKey(room)) {
                    mRooms.get(room).onSysMessage(nickName + "");
                } else {
                    // LogManager.e(" error room name:" + room);
                }
            }
            break;
        case KeyList.NORMAL_MESSAGE_ID:

            if (mRooms.containsKey(room)) {
                String normalmsg = jsonObject.getString(KeyList.NORMAL_MESSAGE);
                mRooms.get(room).onMessage(normalmsg);
            }

            break;
        case KeyList.GETUSERLIST_ID:

            if (mRooms.containsKey(room)) {
                String users = jsonObject.getString(KeyList.GETUSERLIST);
                String[] usercollection = users.split("\\|");
                List<String> usrlist = Arrays.asList(usercollection);
                mRooms.get(room).onUserList(usrlist);
            }
            break;
        case KeyList.SYSTEM_MESSAGE_ID:
            String sysmsg = jsonObject.getString(KeyList.SYSTEM_MESSAGE);
            if (mRooms.containsKey(room)) {
                mRooms.get(room).onSysMessage(sysmsg);
            } else {
                for (IRoomInterface mInterface : mRooms.values()) {
                    mInterface.onSysMessage(sysmsg);
                }
            }
            break;
        case KeyList.SETUSERNICKNAME_ID:

            break;
        case KeyList.ERROR_ID:
            int errorcode = jsonObject.getInt(KeyList.ERROR);
            switch (errorcode) {
            // TODO add error handler
            case KeyList.ERROR_SAMENICKNAME:

                break;
            case KeyList.ERROR_UNKNOW:

                break;

            default:
                break;
            }
            break;
        default:
            break;
        }
    }

}

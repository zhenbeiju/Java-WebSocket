package main.example;

import java.util.List;

public interface IRoomInterface {
    public String RoomName = null;

    public void onMessage(String msg);

    public void onSysMessage(String msg);

    public void onQuit(String nickName);

    public void onJoin(String nickName);

    public void onUserList(List<String> nickNames);

}

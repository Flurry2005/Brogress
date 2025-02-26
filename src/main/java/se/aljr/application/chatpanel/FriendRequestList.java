package se.aljr.application.chatpanel;

import java.util.ArrayList;

public class FriendRequestList {
    private static final ArrayList<FriendRequest> friendRequestList = new ArrayList<>();

    public static ArrayList<FriendRequest> getFriendRequestList(){
        return friendRequestList;
    }

}

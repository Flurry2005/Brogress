package se.aljr.application.Friends;

import se.aljr.application.ImageAvatar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Friend {
    private final ImageAvatar imageAvatar;
    private final ImageAvatar imageAvatarSocial;
    private String friendName;
    private String friendEmail;
    private boolean isOnline;
    private ArrayList<HashMap<String, String>> chat;
    private JPanel messageStorage;
    public boolean firstLoadIn = true;

    public Friend(){
        imageAvatar = new ImageAvatar();
        imageAvatarSocial = new ImageAvatar();
        if(isOnline){
            imageAvatar.setGradientColor1(Color.GREEN);
            imageAvatar.setGradientColor2(new Color(4, 89, 1));
            imageAvatarSocial.setGradientColor1(Color.GREEN);
            imageAvatarSocial.setGradientColor2(new Color(4, 89, 1));
        }else {
            imageAvatar.setGradientColor1(Color.RED);
            imageAvatar.setGradientColor2(new Color(255, 87, 87));
            imageAvatarSocial.setGradientColor1(Color.RED);
            imageAvatarSocial.setGradientColor2(new Color(255, 87, 87));
        }
        imageAvatar.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        imageAvatar.setAlignmentX(JComponent.LEFT_ALIGNMENT);

    }

    public void updateOnlineStatus(){
        if(isOnline){
            imageAvatar.setGradientColor1(Color.GREEN);
            imageAvatar.setGradientColor2(new Color(12, 176, 9));
            imageAvatarSocial.setGradientColor1(Color.GREEN);
            imageAvatarSocial.setGradientColor2(new Color(4, 89, 1));
        }else {
            imageAvatar.setGradientColor1(Color.RED);
            imageAvatar.setGradientColor2(new Color(255, 87, 87));
            imageAvatarSocial.setGradientColor1(Color.RED);
            imageAvatarSocial.setGradientColor2(new Color(255, 87, 87));
        }
    }

    public ImageAvatar getImageAvatar(){
        return imageAvatar;
    }

    public ImageAvatar getImageAvatarSocial(){
        return imageAvatarSocial;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public ArrayList<HashMap<String, String>> getChat() {
        return chat;
    }

    public void setChat(ArrayList<HashMap<String, String>> chat) {
        this.chat = chat;
    }

    public JPanel getMessageStorage() {
        return messageStorage;
    }

    public void setMessageStorage(JPanel messageStorage) {
        this.messageStorage = messageStorage;
    }
}

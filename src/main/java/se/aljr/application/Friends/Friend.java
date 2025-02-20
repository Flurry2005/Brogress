package se.aljr.application.Friends;

import se.aljr.application.ImageAvatar;

import javax.swing.*;
import java.awt.*;

public class Friend {
    private ImageAvatar imageAvatar;
    private String friendName;
    private String friendEmail;
    private boolean isOnline;

    public Friend(boolean isOnline){
        this.isOnline = isOnline;
        imageAvatar = new ImageAvatar();
        if(isOnline){
            imageAvatar.setGradientColor1(Color.GREEN);
            imageAvatar.setGradientColor2(new Color(4, 89, 1));
        }else {
            imageAvatar.setGradientColor1(Color.RED);
            imageAvatar.setGradientColor2(new Color(255, 87, 87));
        }

        imageAvatar.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        imageAvatar.setAlignmentX(JComponent.LEFT_ALIGNMENT);
    }

    public ImageAvatar getImageAvatar(){
        return imageAvatar;
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

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}

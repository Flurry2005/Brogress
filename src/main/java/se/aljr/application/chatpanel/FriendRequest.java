package se.aljr.application.chatpanel;

import se.aljr.application.ImageAvatar;

import javax.swing.*;
import java.awt.*;

public class FriendRequest {
    private final ImageAvatar imageAvatarFriendRequest;
    private String friendName;
    private String friendEmail;

    public FriendRequest(){
        imageAvatarFriendRequest = new ImageAvatar();

        imageAvatarFriendRequest.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        imageAvatarFriendRequest.setAlignmentX(JComponent.LEFT_ALIGNMENT);

        imageAvatarFriendRequest.setGradientColor1(new Color(129, 129, 129));
        imageAvatarFriendRequest.setGradientColor2(new Color(71, 71, 71));
    }

    public ImageAvatar getImageAvatarFriendRequest(){
        return imageAvatarFriendRequest;
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

}

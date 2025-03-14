package se.aljr.application;

import java.awt.*;
import java.io.File;
@SuppressWarnings("CallToPrintStackTrace")
public class CustomFont {
    static Font font;

    public static Font getFont (){
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File(ResourcePath.getResourcePath("BebasNeue-Regular.otf")));
            font = font.deriveFont(30f);
        }catch(Exception e){
            font = new Font("Arial", Font.BOLD, 40);
            e.printStackTrace();
        }
        return font;
    }
}

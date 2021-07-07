package com.unbrick.xbox.Xbox;

import android.os.Build;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


/**
 * Created by Admin on 08.12.2016.
 */

public class xNotify {

    private final String message;
    private final int type;
    private static XboxSocket xboxSocket;

    public static class Type{
        public static int Game_Inv = 1;
        public static int Friend_Request = 2;
        public static int Message_Small = 3;
        public static int Message_Big = 5;
        public static int Signed_Out = 6;
        public static int Signed_In = 7;
        public static int Signed_In_To_Xbl = 8;
        public static int Signed_In_Offline = 9;
        public static int Wants_To_Chat = 10;
        public static int Disconnected_From_Xbl = 11;
        public static int Downloaded = 12;
        public static int Music = 13;
        public static int Smiely_Face = 14;
        public static int Sad_Face = 15;
        public static int Hammer = 16;
        public static int Reinsert_Memory_Unit = 18;
        public static int Reconnect_Controller = 19;
        public static int Has_Joined_Chat = 20;
        public static int Has_Left_Chat = 21;
        public static int Game_Invite_Sent = 22;
        public static int Page_Sent_To = 24;
        public static int Achivement_Unlocked_G = 27;
        public static int Wants_To_Talk_In_Video_Kinect = 29;
    }

    private xNotify(String message, int type) {
        this.message = message;
        this.type = type;
        if (xboxSocket == null){
            xboxSocket = XboxSocket.getInstance();
        }
    }

    public static xNotify make(String text, int type){
        return new xNotify(toHex(text),type);
    }

    public void send(){
        xboxSocket.xbdm.xNotify(message,type);
    }

    public static String toHex(String arg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            return String.format("%x", new BigInteger(1, arg.getBytes(StandardCharsets.US_ASCII)));
        else
            return String.format("%x", new BigInteger(1, arg.getBytes(Charset.defaultCharset())));
    }
}

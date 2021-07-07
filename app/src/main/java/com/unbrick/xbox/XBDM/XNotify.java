package com.unbrick.xbox.XBDM;

import com.unbrick.xbox.RPC.XDRPCArgumentInfo;
import com.unbrick.xbox.Xbox.XboxSocket;

import java.util.ArrayList;

public class XNotify {

    public enum XNotifyLogo {
        ACHIEVEMENT_UNLOCKED(0x1b),
        ACHIEVEMENTS_UNLOCKED(0x27),
        AVATAR_AWARD_UNLOCKED(60),
        BLANK(0x2a),
        CANT_CONNECT_XBL_PARTY(0x38),
        CANT_DOWNLOAD_X(0x20),
        CANT_SIGN_IN_MESSENGER(0x2b),
        DEVICE_FULL(0x24),
        DISCONNECTED_FROM_XBOX_LIVE(11),
        DISCONNECTED_XBOX_LIVE_11_MINUTES_REMAINING(0x2e),
        DISCONNECTED_XBOX_LIVE_PARTY(0x36),
        DOWNLOAD(12),
        DOWNLOAD_STOPPED_FOR_X(0x21),
        DOWNLOADED(0x37),
        FAMILY_TIMER_X_TIME_REMAINING(0x2d),
        FLASH_LOGO(0x17),
        FLASHING_CHAT_ICON(0x26),
        FLASHING_CHAT_SYMBOL(0x41),
        FLASHING_DOUBLE_SIDED_HAMMER(0x10),
        FLASHING_FROWNING_FACE(15),
        FLASHING_HAPPY_FACE(14),
        FLASHING_MUSIC_SYMBOL(13),
        FLASHING_XBOX_CONSOLE(0x22),
        FLASHING_XBOX_LOGO(4),
        FOUR_2(0x19),
        FOUR_3(0x1a),
        FOUR_5(0x30),
        FOUR_7(0x25),
        FOUR_9(0x1c),
        FRIEND_REQUEST_LOGO(2),
        GAME_INVITE_SENT(0x16),
        GAME_INVITE_SENT_TO_XBOX_LIVE_PARTY(0x33),
        GAMER_PICTURE_UNLOCKED(0x3b),
        GAMERTAG_HAS_JOINED_CHAT(20),
        GAMERTAG_HAS_JOINED_XBL_PARTY(0x39),
        GAMERTAG_HAS_LEFT_CHAT(0x15),
        GAMERTAG_HAS_LEFT_XBL_PARTY(0x3a),
        GAMERTAG_SENT_YOU_A_MESSAGE(5),
        GAMERTAG_SIGNED_IN_OFFLINE(9),
        GAMERTAG_SIGNED_INTO_XBOX_LIVE(8),
        GAMERTAG_SIGNEDIN(7),
        GAMERTAG_SINGED_OUT(6),
        GAMERTAG_WANTS_TO_CHAT(10),
        GAMERTAG_WANTS_TO_CHAT_2(0x11),
        GAMERTAG_WANTS_TO_TALK_IN_VIDEO_KINECT(0x1d),
        GAMERTAG_WANTS_YOU_TO_JOIN_AN_XBOX_LIVE_PARTY(0x31),
        JOINED_XBL_PARTY(0x3d),
        KICKED_FROM_XBOX_LIVE_PARTY(0x34),
        KINECT_HEALTH_EFFECTS(0x2f),
        MESSENGER_DISCONNECTED(0x29),
        MISSED_MESSENGER_CONVERSATION(0x2c),
        NEW_MESSAGE(3),
        NEW_MESSAGE_LOGO(1),
        NULLED(0x35),
        PAGE_SENT_TO(0x18),
        PARTY_INVITE_SENT(50),
        PLAYER_MUTED(0x3f),
        PLAYER_UNMUTED(0x40),
        PLEASE_RECONNECT_CONTROLLERM(0x13),
        PLEASE_REINSERT_MEMORY_UNIT(0x12),
        PLEASE_REINSERT_USB_STORAGE_DEVICE(0x3e),
        READY_TO_PLAY(0x1f),
        UPDATING(0x4c),
        VIDEO_CHAT_INVITE_SENT(30),
        X_HAS_SENT_YOU_A_NUDGE(40),
        X_SENT_YOU_A_GAME_MESSAGE(0x23),
        XBOX_LOGO(0);

        public final int number;

        XNotifyLogo(int number) {
            this.number = number;
        }
    }

    public static void notify(String message) {
        notify(XNotifyLogo.XBOX_LOGO, 0, message);
    }


    public static void notify(XNotifyLogo logo, int playerIndex, String message) {
        XDRPCArgumentInfo<Integer> type = new XDRPCArgumentInfo<>(logo.number);
        XDRPCArgumentInfo<Integer> foo1 = new XDRPCArgumentInfo<>(playerIndex);
        XDRPCArgumentInfo<Integer> foo2 = new XDRPCArgumentInfo<>(2);
        XDRPCArgumentInfo<String> xnotify = new XDRPCArgumentInfo<>("Force host: Enabled!");
        XDRPCArgumentInfo<Byte> _byte = new XDRPCArgumentInfo<>((byte)0);
        XboxSocket.getInstance().xbdm.callOrdinal("xam.xex", 0x290, new ArrayList<XDRPCArgumentInfo>() {{
            add(type);
            add(foo1);
            add(foo2);
            add(xnotify);
            add(_byte);
        }});
    }
}

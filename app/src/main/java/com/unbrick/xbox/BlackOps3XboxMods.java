package com.unbrick.xbox;

import java.math.BigInteger;
import java.util.ArrayList;

import com.unbrick.xbox.Fragments.XboxCommands;
import com.unbrick.xbox.XBDM.listener.OnGamertagsFetchedListener;
import com.unbrick.xbox.Xbox.XboxSocket;

/**
 * Created by Admin on 11.09.2016.
 */
public class BlackOps3XboxMods {

    static int ZM = 1;
    static int MP = 2;

    static XboxSocket xbox = XboxSocket.getInstance();

    private static void messageKillfeed(int game, String message) {
        sendMessageKillfeedBlackOpsIII(message);
    }

    public static class OffHost {
        public static void redBoxes(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("82610920", "60000000");
                xbox.xbdm.setMem("82610948", "60000000");
            } else {
                xbox.xbdm.setMem("82610920", "418200DC");
                xbox.xbdm.setMem("82610948", "418200B4");
            }
        }

        public static void uav(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("8228bc78", "FF");
            } else {
                xbox.xbdm.setMem("8228bc78", "41");
            }
        }

        public static void charms(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("824b0898", "3200FFFF");
            } else {
                xbox.xbdm.setMem("824b0898", "7CF03B78");
            }
        }

        public static void wallRun(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("821beac4", "38600001");
            } else {
                xbox.xbdm.setMem("821beac4", "554B0630");
            }
        }

        public static void noRecoil(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("82279cb8", "60000000");
            } else {
                xbox.xbdm.setMem("82279cb8", "4BF79239");
            }
        }

        public static void smallCrosshair(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("82099fa8", "00000000");
            } else {
                xbox.xbdm.setMem("82099fa8", "42C60000");
            }
        }

        public static void noGunSway(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("82201008", "60000000");
            } else {
                xbox.xbdm.setMem("82201008", "4BFFE659");
            }
        }

        public static void finalStand(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("8219195c", "38600001");
            } else {
                xbox.xbdm.setMem("8219195c", "38600000");
            }
        }

        public static void disableDvarAC() {
            xbox.xbdm.setMem("826b81d0", "60000000");
            xbox.xbdm.setMem("826b818c", "60000000");
        }

        public static void removeColdBlooded() {
            xbox.xbdm.setMem("821f6d1c", "60000000");
        }

    }

    public static class Multiplayer {

        public static void godMode(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("84571643", "05");
            } else {
                xbox.xbdm.setMem("84571643", "04");
            }
        }

        public static void increaseSpeed(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("84577404", "4090");
            } else {
                xbox.xbdm.setMem("84577404", "3F80");
            }
        }

        public static void unlimitedAmmo() {
            xbox.xbdm.setMem("84571b5c", "00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF");
        }

        public static void increaseJumpHeight(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("8209c764", "43960000");
            } else {
                xbox.xbdm.setMem("8209c764", "421C0000");
            }
        }

        public static void forceHost() {
            xbox.xbdm.write("consolefeatures ver=2 type=0 system as=0 params=\"A\\8263A6A0\\A\\2\\1\\0\\7/80\\70617274795F636F6E6E656374546F4F746865727320303B616C6C6F77416C6C4E415420313B70617274795F6D696E706C617965727320313B70617274794D6967726174655F64697361626C65642031\\\"");
        }

    }

    public static void getGamertagsBlackOpsIII(OnGamertagsFetchedListener onGamertagsFetchedListener) {
        ArrayList<String> gamertags = new ArrayList<>();
        for (String offset : XboxCommands.BlackOpsGamertagOffsets.bo3zmOffsets) {
            xbox.xbdm.getMem(offset, gamertag -> {
                if (gamertag != null && gamertag.length() > 0) {
                    gamertags.add(gamertag);
                }
            });
        }
        onGamertagsFetchedListener.fetched(gamertags);
    }

    public static void sendMessageKillfeedBlackOpsIII(String message) {
        String finalString = "consolefeatures ver=2 type=0 as=0 params=\"A\\82665968\\A\\3\\1\\0\\1\\0\\7/27\\3c2022" + String.format("%x", new BigInteger(1, message.getBytes())) + "22\\\"";
        //xbdm.write(finalString);
    }

    public static class Zombies {

        public static void giveWeapon(int weaponID, int player, boolean first){
            xbox.xbdm.setMem(
                            first ?
                                    Integer.toHexString(0x845163db + (player - 1) * 0x61B8) :
                                    Integer.toHexString(0x8451642b + (player - 1) * 0x61B8) ,
                            Integer.toHexString(weaponID));
        }

        public static void godMode(int player, boolean enabled) {
            String offset = Integer.toHexString(0x845160f3 + (player - 1) * 0x61B8);
            if (enabled) {
                xbox.xbdm.setMem(offset, "05");
                messageKillfeed(ZM, "GodMode Player " + player + ": ^2Enabled");
            } else {
                xbox.xbdm.setMem(offset, "04");
                messageKillfeed(ZM, "GodMode Player " + player + ": ^2Enabled");
            }
        }

        public static void giveMoney(int player) {
            String offset = Integer.toHexString(0x8451bf5c + (player - 1) * 0x61B8);
            xbox.xbdm.setMem(offset, "000F423F");
            messageKillfeed(ZM, "^2Money given to Player " + player);
        }

        public static void giveAmmo(int player) {
            String offset = Integer.toHexString(0x8451660c + (player - 1) * 0x61B8);
            xbox.xbdm.setMem(offset, "00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF");
            messageKillfeed(ZM, "^2Ammo given to Player " + player);
        }

    }
}

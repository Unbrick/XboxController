package com.unbrick.xbox;

import com.unbrick.xbox.RPC.XDRPCArgumentInfo;
import com.unbrick.xbox.XBDM.XNotify;
import com.unbrick.xbox.XBDM.listener.OnGamertagsFetchedListener;
import com.unbrick.xbox.Xbox.XboxSocket;

import java.util.ArrayList;

import static com.unbrick.xbox.Offsets.OffHost.charms;
import static com.unbrick.xbox.Offsets.OffHost.charmsDisabled;
import static com.unbrick.xbox.Offsets.OffHost.charmsEnabled;
import static com.unbrick.xbox.Offsets.OffHost.laser;
import static com.unbrick.xbox.Offsets.OffHost.laserDisabled;
import static com.unbrick.xbox.Offsets.OffHost.laserEnabled;
import static com.unbrick.xbox.Offsets.OffHost.noRecoil;
import static com.unbrick.xbox.Offsets.OffHost.noRecoilActive;
import static com.unbrick.xbox.Offsets.OffHost.noRecoilInactive;
import static com.unbrick.xbox.Offsets.OffHost.radar;
import static com.unbrick.xbox.Offsets.OffHost.radarDisabled;
import static com.unbrick.xbox.Offsets.OffHost.radarEnabled;
import static com.unbrick.xbox.Offsets.OffHost.redBoxes;
import static com.unbrick.xbox.Offsets.OffHost.redBoxesDisabled;
import static com.unbrick.xbox.Offsets.OffHost.redBoxesEnabled;
import static com.unbrick.xbox.Offsets.ZM.bo2zmOffsets;
import static com.unbrick.xbox.Offsets.ZM.fasterPlayerSpeed;
import static com.unbrick.xbox.Offsets.ZM.fasterPlayerSpeedDisabled;
import static com.unbrick.xbox.Offsets.ZM.fasterPlayerSpeedEnabled;
import static com.unbrick.xbox.Offsets.ZM.godMode;
import static com.unbrick.xbox.Offsets.ZM.godModeDisabled;
import static com.unbrick.xbox.Offsets.ZM.godModeEnabled;
import static com.unbrick.xbox.Offsets.ZM.money;
import static com.unbrick.xbox.Offsets.ZM.money100k;
import static com.unbrick.xbox.Offsets.ZM.noTarget;
import static com.unbrick.xbox.Offsets.ZM.noTargetDisabled;
import static com.unbrick.xbox.Offsets.ZM.noTargetEnabled;
import static com.unbrick.xbox.Offsets.ZM.playerOffset;
import static com.unbrick.xbox.Offsets.ZM.unlimitedAmmo1;
import static com.unbrick.xbox.Offsets.ZM.unlimitedAmmo2;
import static com.unbrick.xbox.Offsets.ZM.unlimitedAmmoDisabled;
import static com.unbrick.xbox.Offsets.ZM.unlimitedAmmoEnabled;

/**
 * Created by Admin on 11.09.2016.
 */
public class BlackOps2XboxMods {

    private static XboxSocket xbox = XboxSocket.getInstance();
    
    public static class OffHost{

        public static void forceHost(boolean enabled) {
            if (enabled) {
                cbufAddtext( 0, "party_connectToOthers 00; partyMigrate_disabled 01; set party_minplayers 1; set party_gamestarttimelength 2; set party_pregamestarttimerlength 2; set party_timer 25;");
                XNotify.notify("Force host: Enabled!");
            } else {
                cbufAddtext( 0, "party_connectToOthers 01; partyMigrate_disabled 00;");
                XNotify.notify("Force host: Disabled!");
            }
        }
        
        public static void noRecoil(boolean enabled){
            xbox.xbdm.setMem(noRecoil, enabled ? noRecoilActive : noRecoilInactive);
            sendMessageKillfeedBlackOpsII("NoRecoil" + (enabled ? ": ^2Enabled" : "^1Disabled"));
        }

        public static void laser(boolean enabled){
            xbox.xbdm.setMem(laser, enabled ? laserEnabled : laserDisabled);
            sendMessageKillfeedBlackOpsII("Laser" + (enabled ? ": ^2Enabled" : "^1Disabled"));
        }

        public static void redBoxes(boolean enabled){
            xbox.xbdm.setMem(redBoxes, enabled ? redBoxesEnabled : redBoxesDisabled);
            sendMessageKillfeedBlackOpsII("RedBoxes" + (enabled ? ": ^2Enabled" : "^1Disabled"));
        }

        public static void charms(boolean enabled){
            xbox.xbdm.setMem(charms, enabled ? charmsEnabled : charmsDisabled);
            sendMessageKillfeedBlackOpsII("Charms" + (enabled ? ": ^2Enabled" : "^1Disabled"));
        }

        public static void radar(boolean enabled){
            xbox.xbdm.setMem(radar, enabled ? radarEnabled : radarDisabled);
            sendMessageKillfeedBlackOpsII("Radar" + (enabled ? ": ^2Enabled" : "^1Disabled"));
        }
    }

    public static class Zombies {

        public static void giveWeapon(int weaponID, int player){
            xbox.xbdm.setMem(Integer.toHexString(0x83551c77 + (player - 1) * 0x61B8), Integer.toHexString(weaponID));
        }

        public static void noTarget(int player, boolean enabled) {
            int _playerOffset = noTarget + (player * playerOffset);
            xbox.xbdm.setMem(_playerOffset, enabled ? noTargetEnabled : noTargetDisabled);
            sendMessageKillfeedBlackOpsII("No target Player " + player + 1 + (enabled ? ": ^2Enabled" : "^1Disabled"));
        }

        public static void fasterPlayerSpeed(int player, boolean enabled) {
            int _playerOffset = fasterPlayerSpeed + (player * playerOffset);
            xbox.xbdm.setMem(_playerOffset, enabled ? fasterPlayerSpeedEnabled : fasterPlayerSpeedDisabled);
            sendMessageKillfeedBlackOpsII("Fast speed player " + player + 1 + (enabled ? ": ^2Enabled" : "^1Disabled"));
        }

        public static void godMode(int player, boolean enabled) {
            int _playerOffset = godMode + (player * playerOffset);
            xbox.xbdm.setMem(_playerOffset, enabled ? godModeEnabled : godModeDisabled);
            sendMessageKillfeedBlackOpsII("GodMode Player " + player + 1 + (enabled ? ": ^2Enabled" : "^1Disabled"));
        }

        public static void unlimitedAmmo(int player, boolean enabled) {
            int _playerOffset1 = unlimitedAmmo1 + (player * playerOffset);
            int _playerOffset2 = unlimitedAmmo2 + (player * playerOffset);
            xbox.xbdm.setMem(_playerOffset1, enabled ? unlimitedAmmoEnabled : unlimitedAmmoDisabled);
            xbox.xbdm.setMem(_playerOffset2, enabled ? unlimitedAmmoEnabled : unlimitedAmmoDisabled);
            sendMessageKillfeedBlackOpsII("UnlimitedAmmo Player " + player + 1 + (enabled ? ": ^2Enabled" : "^1Disabled"));
        }

        public static void giveMoney(int player) {
            int _playerOffset = money + (player * playerOffset);
            xbox.xbdm.setMem(_playerOffset, money100k);
            sendMessageKillfeedBlackOpsII("100k given to player " + player + 1);
        }
    }

    public static void sendMessageKillfeedBlackOpsII(String _msg) {
        sendGameServerCommand(0, "O \"" + _msg +"!\"");
    }

    public static void getGamertagsBlackOpsII(OnGamertagsFetchedListener onGamertagsFetchedListener) {
        ArrayList<String> gamertags = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int addressToRead = bo2zmOffsets + i * playerOffset;
            String stringAddress = Integer.toHexString(addressToRead);
            System.out.println("pass " + i + " addr " + stringAddress);
            int finalI = i;
            xbox.xbdm.getMem(stringAddress, result -> {

                StringBuilder output = new StringBuilder("");
                for (int j = 0; j < result.length(); j += 2) {
                    String str = result.substring(j, j + 2);
                    if (str.equals("00")) {
                        break;
                    }
                    output.append((char) Integer.parseInt(str, 16));
                }
                System.out.println("Output: " + output.toString());
                String gamertag = output.toString();
                if (gamertag.length() > 0) {
                    gamertags.add(gamertag);
                    System.out.println("Gamertag: " + gamertag);
                }
                if (finalI == 3)
                    onGamertagsFetchedListener.fetched(gamertags);
            });
        }
    }

    public static class Multiplayer {

        public static void godMode(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("83551a2b", "A5");
                sendMessageKillfeedBlackOpsII("GodMode : ^2Enabled");
            } else {
                xbox.xbdm.setMem("83551a2b", "04");
                sendMessageKillfeedBlackOpsII("GodMode : ^1Disabled");
            }

        }

        public static void hudHardcore(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("83557017", "02");
                sendMessageKillfeedBlackOpsII("HardcoreHUD : ^2Enabled");
            } else {
                xbox.xbdm.setMem("83557017", "01");
                sendMessageKillfeedBlackOpsII("HardcoreHUD : ^1Disabled");
            }
        }

        public static void freezePlacer(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("83556ef0", "0000");
                sendMessageKillfeedBlackOpsII("FreezePlayer : ^2Enabled");
            } else {
                xbox.xbdm.setMem("83556ef0", "3F80");
                sendMessageKillfeedBlackOpsII("FreezePlayer : ^1Disabled");
            }
        }

        public static void lowGravity(boolean enabled) {
            if (enabled) {
                cbufAddtext(0, "bg_gravity 20");
                sendMessageKillfeedBlackOpsII("Low gravity : ^2Enabled");
            } else {
                cbufAddtext(0, "reset bg_gravity");
                sendMessageKillfeedBlackOpsII("LowGravity : ^1Disabled");
            }
        }

        public static void superJump(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("82085654", "47C34D00");
                xbox.xbdm.setMem("82001650", "47C34D00");
                sendMessageKillfeedBlackOpsII("SuperJump : ^2Enabled");
            } else {
                xbox.xbdm.setMem("82085654", "421C0000");
                xbox.xbdm.setMem("82001650", "43000000");
                sendMessageKillfeedBlackOpsII("SuperJump : ^1Disabled");
            }
        }

        public static void unlimitedAmmo(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("83551e4e", "0F");
                xbox.xbdm.setMem("83551e4a", "0F");
                xbox.xbdm.setMem("83551e53", "FF");
                xbox.xbdm.setMem("83551e57", "FF");
                sendMessageKillfeedBlackOpsII("UnlimitedAmmo : ^2Enabled");
            } else {
                xbox.xbdm.setMem("83551e4e", "00");
                xbox.xbdm.setMem("83551e4a", "00");
                xbox.xbdm.setMem("83551e53", "00");
                xbox.xbdm.setMem("83551e57", "00");
                sendMessageKillfeedBlackOpsII("UnlimitedAmmo : ^1Disabled");
            }
        }

        public static void changeTeam(int team) {
            if (team == 1) {
                xbox.xbdm.setMem("83556f07", "01");
                sendMessageKillfeedBlackOpsII("Switched to TEAM 1");
            } else if (team == 2) {
                xbox.xbdm.setMem("83556f07", "02");
                sendMessageKillfeedBlackOpsII("Switched to TEAM 2");
            }
        }

        public static void blur(boolean enabled) {
            if (enabled) {
                sendGameServerCommand(0, "( a 120");
                sendMessageKillfeedBlackOpsII("Blur : ^1Enabled");
            } else {
                sendGameServerCommand(0, "( a 0");
                sendMessageKillfeedBlackOpsII("Blur : ^2Disabled");
            }
        }

        public static void fasterPlayerSpeed(boolean enabled) {
            if (enabled) {
                xbox.xbdm.setMem("83556ef0", "40FF");
                sendMessageKillfeedBlackOpsII("FasterPlayerSpeed : ^2Enabled");
            } else {
                xbox.xbdm.setMem("83556ef0", "3F80");
                sendMessageKillfeedBlackOpsII("FasterPlayerSpeed : ^1Disabled");
            }
        }

        public static void giveKillstreaks() {
            xbox.xbdm.setMem("83551e3e", "01000000010000000001");
        }
    }

    public static void sendGameServerCommand(int player, String command) {
        int address = 0x8242FB70;
        XDRPCArgumentInfo<Integer> _player = new XDRPCArgumentInfo<>(player);
        XDRPCArgumentInfo<Integer> second_argument = new XDRPCArgumentInfo<>(1);
        XDRPCArgumentInfo<String> message = new XDRPCArgumentInfo<>(command);
        xbox.xbdm.callAddress(address, new ArrayList<XDRPCArgumentInfo>() {{
            add(_player);
            add(second_argument);
            add(message);
        }});
    }

    public static void cbufAddtext(int foo1, String command) {
        int address = 0x824015E0;
        XDRPCArgumentInfo<Integer> _foo1 = new XDRPCArgumentInfo<>(foo1);
        XDRPCArgumentInfo<String> message = new XDRPCArgumentInfo<>(command);
        xbox.xbdm.callAddress(address, new ArrayList<XDRPCArgumentInfo>() {{
            add(_foo1);
            add(message);
        }});
    }

}

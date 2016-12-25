package t_r_y.c_a_t_c_h.me;

import t_r_y.c_a_t_c_h.me.Xbox.XboxSocket;

/**
 * Created by Admin on 11.09.2016.
 */
public class BlackOps2XboxMods {

    private static int ZM = 1;
    private static int MP = 2;
    
    private static XboxSocket xbox = XboxSocket.getInstance();
    

    private static void messageKillfeed(int gamemode, String message) {
        xbox.sendMessageKillfeedBlackOpsII(message);
    }
    
    public static class OffHost{
        
        public static void noRecoil(boolean enabled){
            if (enabled) {
                xbox.setMem("82259bc8", "60");
                messageKillfeed(MP,"NoRecoil : ^2Enabled");
            }
            else {
                xbox.setMem("82259bc8","48461341");
                messageKillfeed(MP,"NoRecoil : ^1Disabled");
            }
        }

        public static void laser(boolean enabled){
            if (enabled) {
                xbox.setMem("82255E1C","2B000B01");
                messageKillfeed(MP,"Laser : ^2Enabled");
            }
            else {
                xbox.setMem("82255e1c","2B0B0000");
                messageKillfeed(MP,"Laser : ^1Disabled");
            }
        }

        public static void redBoxes(boolean enabled){
            if (enabled) {
                xbox.setMem("821F5B7F","01");
                messageKillfeed(MP,"RedBoxes : ^2Enabled");
            }
            else {
                xbox.setMem("821F5B7F","00");
                messageKillfeed(MP,"RedBoxes : ^1Disabled");
            }
        }

        public static void charms(boolean enabled){
            if (enabled) {
                xbox.setMem("821fc04c","38C0FFFF");
                messageKillfeed(MP,"Charms : ^2Enabled");
            }
            else {
                xbox.setMem("821fc04c","7FA6EB78");
                messageKillfeed(MP,"Charms : ^1Disabled");
            }
        }

        public static void radar(boolean enabled){
            if (enabled) {
                xbox.setMem("821B8FD3","01");
                messageKillfeed(MP,"Radar : ^2Enabled");
            }
            else {
                xbox.setMem("821B8FD3","00");
                messageKillfeed(MP,"Radar : ^1Disabled");
            }
        }
        
    }

    public static class Zombies {

        public static void giveWeapon(int weaponID, int player){
            xbox.setMem(Integer.toHexString(0x83551c77 + (player - 1) * 0x61B8), Integer.toHexString(weaponID));
        }

        public static void godMode(int player, boolean enabled) {
            switch (player) {
                case 1:
                    if (enabled) {
                        xbox.setMem("83556ede", "FFFF");
                        messageKillfeed(ZM, "GodMode Player " + player + ": ^2Enabled");
                    } else {
                        xbox.setMem("83556ede", "0064");
                        messageKillfeed(ZM, "GodMode Player " + player + ": ^1Disabled");
                    }
                    break;
                case 2:
                    if (enabled) {
                        xbox.setMem("8355c6d6", "FFFF");
                        messageKillfeed(ZM, "GodMode Player " + player + ": ^2Enabled");
                    } else {
                        xbox.setMem("8355c6d6", "0064");
                        messageKillfeed(ZM, "GodMode Player " + player + ": ^1Disabled");
                    }
                    break;
                case 3:
                    if (enabled) {
                        xbox.setMem("83561ece", "FFFF");
                        messageKillfeed(ZM, "GodMode Player " + player + ": ^2Enabled");
                    } else {
                        xbox.setMem("83561ece", "0064");
                        messageKillfeed(ZM, "GodMode Player " + player + ": ^1Disabled");
                    }
                    break;
                case 4:
                    if (enabled) {
                        xbox.setMem("835676c6", "FFFF");
                        messageKillfeed(ZM, "GodMode Player " + player + ": ^2Enabled");
                    } else {
                        xbox.setMem("835676c6", "0064");
                        messageKillfeed(ZM, "GodMode Player " + player + ": ^1Disabled");
                    }
                    break;
            }
        }

        public static void unlimitedAmmo(int player, boolean enabled) {
            switch (player) {
                case 1:
                    if (enabled) {
                        xbox.setMem("83551e45", "FFFF");
                        xbox.setMem("83551e3d", "FFFF");
                        messageKillfeed(ZM, "UnlimitedAmmo Player " + player + ": ^2Enabled");
                    } else {
                        xbox.setMem("83556ede", "0000");
                        xbox.setMem("83551e45", "0000");
                        messageKillfeed(ZM, "UnlimitedAmmo Player " + player + ": ^1Disabled");
                    }
                    break;
                case 2:
                    if (enabled) {
                        xbox.setMem("8355763d", "FFFF");
                        xbox.setMem("83557635", "FFFF");
                        messageKillfeed(ZM, "UnlimitedAmmo Player " + player + ": ^2Enabled");
                    } else {
                        xbox.setMem("8355763d", "0000");
                        xbox.setMem("83557635", "0000");
                        messageKillfeed(ZM, "UnlimitedAmmo Player " + player + ": ^1Disabled");
                    }
                    break;
                case 3:
                    if (enabled) {
                        xbox.setMem("8355ce35", "FFFF");
                        xbox.setMem("8355ce2d", "FFFF");
                        messageKillfeed(ZM, "UnlimitedAmmo Player " + player + ": ^2Enabled");
                    } else {
                        xbox.setMem("8355ce35", "0000");
                        xbox.setMem("8355ce2d", "0000");
                        messageKillfeed(ZM, "UnlimitedAmmo Player " + player + ": ^1Disabled");
                    }
                    break;
                case 4:
                    if (enabled) {
                        xbox.setMem("8356262d", "FFFF");
                        xbox.setMem("83562625", "FFFF");
                        messageKillfeed(ZM, "UnlimitedAmmo Player " + player + ": ^2Enabled");
                    } else {
                        xbox.setMem("8356262d", "0000");
                        xbox.setMem("83562625", "0000");
                        messageKillfeed(ZM, "UnlimitedAmmo Player " + player + ": ^1Disabled");
                    }
                    break;
            }
        }

        public static void giveMoney(int player) {
            switch (player) {
                case 1:
                    xbox.setMem("83556fd8", "000186A0");
                    messageKillfeed(ZM, "Money given to Player " + player);
                    break;
                case 2:
                    xbox.setMem("8355c7d0", "000186A0");
                    messageKillfeed(ZM, "Money given to Player " + player);
                    break;
                case 3:
                    xbox.setMem("83561fc8", "000186A0");
                    messageKillfeed(ZM, "Money given to Player " + player);
                    break;
                case 4:
                    xbox.setMem("835677c0", "000186A0");
                    messageKillfeed(ZM, "Money given to Player " + player);
                    break;
            }
        }

    }

    public static class Multiplayer {
        
        public static void forceHost(boolean enabled) {
            if (enabled)
                xbox.plainCommandJRPC2("A\\824015E0\\T\\0\\A\\2\\1\\0\\7/69\\70617274795F636F6E6E656374546F4F746865727320303B2070617274794D6967726174655F64697361626C656420313B2073765F656E6447616D654966495375636B2030\\");
            else
                xbox.plainCommandJRPC2("A\\824015E0\\T\\0\\A\\2\\1\\0\\7/81\\72657365742070617274795F636F6E6E656374546F4F74686572733B2072657365742070617274794D6967726174655F64697361626C65643B2072657365742073765F656E6447616D654966495375636B\\");
        }

        public static void godMode(boolean enabled) {
            if (enabled) {
                xbox.setMem("83551a2b", "A5");
                messageKillfeed(MP, "GodMode : ^2Enabled");
            } else {
                xbox.setMem("83551a2b", "04");
                messageKillfeed(MP, "GodMode : ^1Disabled");
            }

        }

        public static void hudHardcore(boolean enabled) {
            if (enabled) {
                xbox.setMem("83557017", "02");
                messageKillfeed(MP, "HardcoreHUD : ^2Enabled");
            } else {
                xbox.setMem("83557017", "01");
                messageKillfeed(MP, "HardcoreHUD : ^1Disabled");
            }
        }

        public static void freezePlacer(boolean enabled) {
            if (enabled) {
                xbox.setMem("83556ef0", "0000");
                messageKillfeed(MP, "FreezePlayer : ^2Enabled");
            } else {
                xbox.setMem("83556ef0", "3F80");
                messageKillfeed(MP, "FreezePlayer : ^1Disabled");
            }
        }

        public static void lowGravity(boolean enabled) {
            if (enabled) {
                xbox.plainCommandJRPC2("A\\824015E0\\T\\0\\A\\2\\1\\0\\7/13\\62675F67726176697479203230\\");
                xbox.plainCommandJRPC2( "A\\8242FB70\\T\\0\\A\\3\\1\\-1\\1\\1\\7/27\\4F20224C6F772067726176697479203A205E32456E61626C656422\\");
                messageKillfeed(MP, "LowGravity : ^2Enabled");
            } else {
                xbox.plainCommandJRPC2("A\\824015E0\\T\\0\\A\\2\\1\\0\\7/16\\72657365742062675F67726176697479\\");
                xbox.plainCommandJRPC2("A\\8242FB70\\T\\0\\A\\3\\1\\-1\\1\\1\\7/28\\4F20224C6F772067726176697479203A205E3144697361626C656422\\");
                messageKillfeed(MP, "LowGravity : ^1Disabled");
            }
        }

        public static void superJump(boolean enabled) {
            if (enabled) {
                xbox.setMem("82085654", "47C34D00");
                xbox.setMem("82001650", "47C34D00");
                messageKillfeed(MP, "SuperJump : ^2Enabled");
            } else {
                xbox.setMem("82085654", "421C0000");
                xbox.setMem("82001650", "43000000");
                messageKillfeed(MP, "SuperJump : ^1Disabled");
            }
        }

        public static void unlimitedAmmo(boolean enabled) {
            if (enabled) {
                xbox.setMem("83551e4e", "0F");
                xbox.setMem("83551e4a", "0F");
                xbox.setMem("83551e53", "FF");
                xbox.setMem("83551e57", "FF");
                messageKillfeed(MP, "UnlimitedAmmo : ^2Enabled");
            } else {
                xbox.setMem("83551e4e", "00");
                xbox.setMem("83551e4a", "00");
                xbox.setMem("83551e53", "00");
                xbox.setMem("83551e57", "00");
                messageKillfeed(MP, "UnlimitedAmmo : ^1Disabled");
            }
        }

        public static void changeTeam(int team) {
            if (team == 1) {
                xbox.setMem("83556f07", "01");
                messageKillfeed(MP, "Switched to TEAM 1");
            } else if (team == 2) {
                xbox.setMem("83556f07", "02");
                messageKillfeed(MP, "Switched to TEAM 2");
            }
        }

        public static void blur(boolean enabled) {
            if (enabled) {
                xbox.plainCommandJRPC2("A\\8242FB70\\T\\0\\A\\3\\1\\0\\1\\0\\7/7\\28206120313230\\");
                xbox.plainCommandJRPC2("A\\8242FB70\\T\\0\\A\\3\\1\\0\\1\\1\\7/20\\4F2022426C7572203A205E31456E61626C656422\\");
            } else {
                xbox.plainCommandJRPC2("A\\8242FB70\\T\\0\\A\\3\\1\\0\\1\\0\\7/5\\2820612030\\");
                xbox.plainCommandJRPC2("A\\8242FB70\\T\\0\\A\\3\\1\\0\\1\\1\\7/21\\4F2022426C7572203A205E3244697361626C656422\\");
            }
        }

        public static void fasterPlayerSpeed(boolean enabled) {
            if (enabled) {
                xbox.setMem("83556ef0", "40FF");
                messageKillfeed(MP, "FasterPlayerSpeed : ^2Enabled");
            } else {
                xbox.setMem("83556ef0", "3F80");
                messageKillfeed(MP, "FasterPlayerSpeed : ^1Disabled");
            }
        }

        public static void giveKillstreaks() {
            xbox.setMem("83551e3e", "01000000010000000001");
        }
    }

}

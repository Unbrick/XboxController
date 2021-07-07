package com.unbrick.xbox;

public class Offsets {
    static class OffHost {
        static int noRecoil = 0x82259bc8;
        static int noRecoilActive = 60;
        static int noRecoilInactive = 0x48461341;

        static int laser = 0x82255E1C;
        static int laserEnabled = 0x2B000B01;
        static int laserDisabled = 0x2B0B0000;

        static int redBoxes = 0x821F5B7F;
        static int redBoxesEnabled = 0x01;
        static int redBoxesDisabled = 0x00;

        static int charms = 0x821fc04c;
        static int charmsEnabled = 0x38C0FFFF;
        static int charmsDisabled = 0x7FA6EB78;


        static int radar = 0x821B8FD3;
        static int radarEnabled = 0x01;
        static int radarDisabled = 0x00;
    }

    static class ZM {
        public static int bo2zmOffsets = 0x83556EBC;
        public static int playerOffset = 0x57f8;

        static int noTarget = 0x83556e23;
        static int noTargetEnabled = 0xFFFF;
        static int noTargetDisabled = 0x0064;

        static int fasterPlayerSpeed = 0x83556ef0;
        static int fasterPlayerSpeedEnabled = 0x40FF;
        static int fasterPlayerSpeedDisabled = 0x3F80;

        static int godMode = 0x83556ede;
        static int godModeEnabled = 0xFFFF;
        static int godModeDisabled = 0x0064;

        static int unlimitedAmmo1 = 0x83551e45;
        static int unlimitedAmmo2 = 0x83551e3d;
        static int unlimitedAmmoEnabled = 0xFFFFFF;
        static int unlimitedAmmoDisabled = 0x000000;

        static int money =  0x83551A10 + 0x55C8;
        static int money100k = 0x000186A0;
    }

}

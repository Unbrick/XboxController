package t_r_y.c_a_t_c_h.me.Helper;

import java.util.HashMap;

/**
 * Created by Admin on 07.09.2016.
 */
public class Constants {
    public static final int BLACK_OPS_2_ZM_TRANZIT = 1;
    public static final int BLACK_OPS_2_ZM_MOTD = 2;
    public static final int BLACK_OPS_2_ZM_DIERISE = 3;
    public static final int BLACK_OPS_2_ZM_BURIED = 4;
    public static final int BLACK_OPS_2_ZM_ORIGINS = 5;

    public static final int BLACK_OPS_2_ZM = 6;
    public static final int BLACK_OPS_2_MP = 7;
    public static final int BLACK_OPS_3_MP = 8;
    public static final int BLACK_OPS_3_ZM = 9;

    public static final int BLACK_OPS_3_ZM_SHADOWS = 10;
    public static final int BLACK_OPS_3_ZM_EISENDRACHE = 11;
    public static final int BLACK_OPS_3_ZM_GIANT = 12;


    public static String[] bypassOffsets = new String[]{
            "8259A65C",
            "82497EB0",
            "82497F30",
            "82497EE0",
            "82497EC8",
            "82599680",
            "82599670",
            "82599628",
            "8259964C",
            "825996AC",
            "825996B4",
            "825996B4",
            "82599644",
            "824E0CD8",
            "827DB494"
    };
    public static String NOP = "60000000";

    public static HashMap<String,Integer> weaponsTheGiant = new HashMap<>();
    public static HashMap<String,Integer> weaponsShadowsOfEvil = new HashMap<>();
    public static HashMap<String,Integer> weaponsEisendrache = new HashMap<>();

    public static HashMap<String,Integer> weaponsBuried = new HashMap<>();
    public static HashMap<String,Integer> weaponsdieRise = new HashMap<>();
    public static HashMap<String,Integer> weaponsmobOTD = new HashMap<>();
    public static HashMap<String,Integer> weaponsmobTranzit = new HashMap<>();
    public static HashMap<String,Integer> weaponsOrigins = new HashMap<>();


    public static HashMap<String,Integer> weaponsbo3MP = new HashMap<>();
    public static HashMap<String,Integer> weaponsbo2MP = new HashMap<>();
    static{
        weaponsTheGiant.put("Default Weapon",1);
        weaponsTheGiant.put("Vesper",2);
        weaponsTheGiant.put("VMP",4);
        weaponsTheGiant.put("Kuda",6);
        weaponsTheGiant.put("Pharo",8);
        weaponsTheGiant.put("Weevil",10);
        weaponsTheGiant.put("Razorback",12);
        weaponsTheGiant.put("Crocuta ( Kuda Upgraded )",14);
        weaponsTheGiant.put("Whispering Regurgitator ( Pharo Upgraded )",16);
        weaponsTheGiant.put("Infernus ( Vesper Upgraded )",18);
        weaponsTheGiant.put("Razorback Upgraded",20);
        weaponsTheGiant.put("The Impaler ( VMP Upgraded )",21);
        weaponsTheGiant.put("Barage ( Weevil Upgraded )",23);
        weaponsTheGiant.put("IRC-1",25);
        weaponsTheGiant.put("KN-44",27);
        weaponsTheGiant.put("M8A7",29);
        weaponsTheGiant.put("Shieva",31);
        weaponsTheGiant.put("HVK-30",33);
        weaponsTheGiant.put("Man-O-War",35);
        weaponsTheGiant.put("Anointed Avenger ( KN-44 Upgraded )",37);
        weaponsTheGiant.put("Illuminated Deanimator ( IRC-1 Upgraded )",39);
        weaponsTheGiant.put("High Velocity Kicker ( HVK-30 Upgraded )",41);
        weaponsTheGiant.put("Dread Armada ( Man-O-War Upgraded )",43);
        weaponsTheGiant.put("The Unspeakable ( M8A7 Upgraded )",45);
        weaponsTheGiant.put("Cumulus Struggle ( Shieva Upgraded )",47);
        weaponsTheGiant.put("KRM-262",49);
        weaponsTheGiant.put("Argus",50);
        weaponsTheGiant.put("Brecci",51);
        weaponsTheGiant.put("Haymaker 12",52);
        weaponsTheGiant.put("Shoeshining 100 ( Haymaker 12 Upgraded )",53);
        weaponsTheGiant.put("Ancient Messenger ( Argus Upgraded )",54);
        weaponsTheGiant.put("Dagon's Glare ( KRM-262 Upgraded )",55);
        weaponsTheGiant.put("Stellar Screech ( 205 Brecci Upgraded )",56);
        weaponsTheGiant.put("BRM",57);
        weaponsTheGiant.put("Dingo",59);
        weaponsTheGiant.put("Dredge",61);
        weaponsTheGiant.put("Gorgon",63);
        weaponsTheGiant.put("Dire Wolf ( Dingo Upgraded )",65);
        weaponsTheGiant.put("Trapezohedron Shard ( 48 Dredge Upgraded )",67);
        weaponsTheGiant.put("Blight Oblivion ( DRM Upgraded )",69);
        weaponsTheGiant.put("Athena's Spear ( Gorgon Upgraded )",71);
        weaponsTheGiant.put("Locus",73);
        weaponsTheGiant.put("Drakon",75);
        weaponsTheGiant.put("SVG-100",77);
        weaponsTheGiant.put("Arrhythmic Dirge ( Locus Upgraded )",79);
        weaponsTheGiant.put("Bahamut ( Drakon Upgraded )",81);
        weaponsTheGiant.put("Ikken Hissatsu ( SVG-100 Upgraded )",83);
        weaponsTheGiant.put("MR6",85);
        weaponsTheGiant.put("RK5",86);
        weaponsTheGiant.put("L-CAR 9",87);
        weaponsTheGiant.put("Upgraded Starting Pistol (CRASHES THE GAME IF GIVEN)",88);
        weaponsTheGiant.put("Rex-Kalibur 115 ( RK5 Upgraded )",90);
        weaponsTheGiant.put("Flux Collider 935 ( L-CAR 9 Upgraded )",91);
        weaponsTheGiant.put("XM-53",92);
        weaponsTheGiant.put("Heliacal Incandescence ( XM-53 Upgraded )",93);
        weaponsTheGiant.put("Wunderwaffe DG-2",96);
        weaponsTheGiant.put("Wunderwaffe DG-3 JZ ( Wunderwaffe DG-2 Upgraded )",97);
        weaponsTheGiant.put("Gives Explosive Rounds",98);
        weaponsTheGiant.put("Gives Explosive Rounds",99);
        weaponsTheGiant.put("Gives Explosive Rounds (CAUSES CRASH)",100);
        weaponsTheGiant.put("Gives You Explosive Ice Rounds ( Careful Can Kill You )",102);
        weaponsTheGiant.put("Monkey Bombs",107);
        weaponsTheGiant.put("Frag Grenade",108);
        weaponsTheGiant.put("Porter's X2 Ray Gun",112);
        weaponsTheGiant.put("Ray Gun",113);

        /***********************************************/
        weaponsShadowsOfEvil.put("Default Weapon ",1);
        weaponsShadowsOfEvil.put("Vesper",2);
        weaponsShadowsOfEvil.put("VMP",4);
        weaponsShadowsOfEvil.put("Bootlegger",6);
        weaponsShadowsOfEvil.put("Kuda",7);
        weaponsShadowsOfEvil.put("Pharo",9);
        weaponsShadowsOfEvil.put("Weevil",11);
        weaponsShadowsOfEvil.put("Razorback",13);
        weaponsShadowsOfEvil.put("Crocuta ( Kuda Upgraded )",15);
        weaponsShadowsOfEvil.put("Whispering Regurgitator ( Pharo Upgraded )",17);
        weaponsShadowsOfEvil.put("Infernus ( Vesper Upgraded )",19);
        weaponsShadowsOfEvil.put("Razorback Upgraded",21);
        weaponsShadowsOfEvil.put("The Impaler ( VMP Upgraded )",22);
        weaponsShadowsOfEvil.put("Barage ( Weevil Upgraded )",24);
        weaponsShadowsOfEvil.put("Ein Sten ( Bootlegger Upgraded )",26);
        weaponsShadowsOfEvil.put("IRC-1",27);
        weaponsShadowsOfEvil.put("KN-44",29);
        weaponsShadowsOfEvil.put("M8A7",31);
        weaponsShadowsOfEvil.put("Sheiva",33);
        weaponsShadowsOfEvil.put("HVK-30",35);
        weaponsShadowsOfEvil.put("Man-O-War",37);
        weaponsShadowsOfEvil.put("Anointed Avenger ( KN-44 Upgraded )",39);
        weaponsShadowsOfEvil.put("Illuminated Deanimator ( IRC-1 Upgraded )",41);
        weaponsShadowsOfEvil.put("High Velocity Kicker ( HVK-30 Upgraded )",43);
        weaponsShadowsOfEvil.put("Dread Armada ( Man-O-War Upgraded )",45);
        weaponsShadowsOfEvil.put("The Unspeakable ( M8A7 Upgraded )",47);
        weaponsShadowsOfEvil.put("Cumulus Struggle ( Shieva Upgraded )",49);
        weaponsShadowsOfEvil.put("KRM-262",51);
        weaponsShadowsOfEvil.put("Argus",52);
        weaponsShadowsOfEvil.put("Brecci",53);
        weaponsShadowsOfEvil.put("Haymaker 12",54);
        weaponsShadowsOfEvil.put("Shoeshining 100 ( Haymaker 12 Upgraded )",55);
        weaponsShadowsOfEvil.put("Ancient Messenger ( Argus Upgraded )",56);
        weaponsShadowsOfEvil.put("Dagon's Glare ( KRM-262 Upgraded )",57);
        weaponsShadowsOfEvil.put("Stellar Screech ( 205 Brecci Upgraded )",58);
        weaponsShadowsOfEvil.put("BRM",59);
        weaponsShadowsOfEvil.put("Dingo",61);
        weaponsShadowsOfEvil.put("Dredge",63);
        weaponsShadowsOfEvil.put("Gorgon",65);
        weaponsShadowsOfEvil.put("Dire Wolf ( Dingo Upgraded )",67);
        weaponsShadowsOfEvil.put("Trapezohedron Shard ( 48 Dredge Upgraded )",69);
        weaponsShadowsOfEvil.put("Blight Oblivion ( DRM Upgraded )",71);
        weaponsShadowsOfEvil.put("Athena's Spear ( Gorgon Upgraded )",73);
        weaponsShadowsOfEvil.put("Locus",75);
        weaponsShadowsOfEvil.put("Drakon",77);
        weaponsShadowsOfEvil.put("SVG-100",79);
        weaponsShadowsOfEvil.put("Arrhythmic Dirge ( Locus Upgraded )",81);
        weaponsShadowsOfEvil.put("Bahamut ( Drakon Upgraded )",83);
        weaponsShadowsOfEvil.put("Ikken Hissatsu ( SVG-100 Upgraded )",85);
        weaponsShadowsOfEvil.put("MR6",87);
        weaponsShadowsOfEvil.put("Bloodhound",88);
        weaponsShadowsOfEvil.put("RK5",89);
        weaponsShadowsOfEvil.put("L-CAR-9",90);
        weaponsShadowsOfEvil.put("Upgraded THE GIANT (Der Reise) Starting Pistol (CRASHES THE GAME IF GIVEN)",91);
        weaponsShadowsOfEvil.put("Rex-Kalibur 115 ( RK5 Upgraded )",93);
        weaponsShadowsOfEvil.put("Flux Collider 935 ( L-CAR 9 Upgraded )",94);
        weaponsShadowsOfEvil.put("Upgraded Starting Pistol (CRASHES THE GAME IF GIVEN)",95);
        weaponsShadowsOfEvil.put("XM-53",97);
        weaponsShadowsOfEvil.put("Heliacal Incandescence ( XM-53 Upgraded )",98);
        weaponsShadowsOfEvil.put("Wunderwaffe DG-2",101);
        weaponsShadowsOfEvil.put("Wunderwaffe DG-3 JZ ( Wunderwaffe DG-2 Upgraded )",102);
        weaponsShadowsOfEvil.put("Gives You Explosive Rounds",104);
        weaponsShadowsOfEvil.put("Kor-Maroth ( Different Versions )",125);
        weaponsShadowsOfEvil.put("Alien Jar Grenade",136);
        weaponsShadowsOfEvil.put("Porters X2 Ray Gun",138);
        weaponsShadowsOfEvil.put("Ray Gun",139);
        weaponsShadowsOfEvil.put("The Beast ( Weapon From Beast )",148);

        /******************************************************************/

        weaponsEisendrache.put("Default Weapon",1);
        weaponsEisendrache.put("Vesper",2);
        weaponsEisendrache.put("VMP",4);
        weaponsEisendrache.put("Kuda",6);
        weaponsEisendrache.put("Pharo",8);
        weaponsEisendrache.put("Weevil",10);
        weaponsEisendrache.put("Razorback",12);
        weaponsEisendrache.put("Crocuta (PAP Kuda)",14);
        weaponsEisendrache.put("Whispering Regurgitator (PAP Pharo)",16);
        weaponsEisendrache.put("Infernus (PAP Vesper)",18);
        weaponsEisendrache.put("Gullinbursti (PAP Razorback)",20);
        weaponsEisendrache.put("The Impaler (PAP VMP)",21);
        weaponsEisendrache.put("Barage (PAP Weevil)",23);
        weaponsEisendrache.put("IRC-1",25);
        weaponsEisendrache.put("KN-44",27);
        weaponsEisendrache.put("M8A7",29);
        weaponsEisendrache.put("Shieva",31);
        weaponsEisendrache.put("HVK-30",33);
        weaponsEisendrache.put("Man-O-War",35);
        weaponsEisendrache.put("Anointed Avenger (PAP KN-44)",37);
        weaponsEisendrache.put("Illuminated Deanimator (PAP IRC-1)",39);
        weaponsEisendrache.put("High Velocity Kicker (PAP HVK-30)",41);
        weaponsEisendrache.put("Dread Armada (PAP Man-O-War)",43);
        weaponsEisendrache.put("The Unspeakable (PAP M8A7)",45);
        weaponsEisendrache.put("Cumulus Struggle (PAP Shieva)",47);
        weaponsEisendrache.put("KRM-262",49);
        weaponsEisendrache.put("Argus",50);
        weaponsEisendrache.put("205 Brecci",51);
        weaponsEisendrache.put("Haymaker 12",52);
        weaponsEisendrache.put("Shoeshining 100 (PAP Haymaker 12)",53);
        weaponsEisendrache.put("Ancient Messenger (PAP Argus)",54);
        weaponsEisendrache.put("Dagon's Glare (PAP KRM-262)",55);
        weaponsEisendrache.put("Stellar Screech (PAP 205 Brecci)",56);
        weaponsEisendrache.put("BRM",57);
        weaponsEisendrache.put("Dingo",59);
        weaponsEisendrache.put("48 Dredge",61);
        weaponsEisendrache.put("Gorgon",63);
        weaponsEisendrache.put("Dire Wolf (PAP Dingo)",65);
        weaponsEisendrache.put("Trapezohedron Shard (PAP 48 Dredge)",67);
        weaponsEisendrache.put("Blight Oblivion (PAP DRM)",69);
        weaponsEisendrache.put("Athena's Spear (PAP Gorgon)",71);
        weaponsEisendrache.put("Locus",73);
        weaponsEisendrache.put("Drakon",75);
        weaponsEisendrache.put("SVG-100",77);
        weaponsEisendrache.put("Arrhythmic Dirge (PAP Locus)",79);
        weaponsEisendrache.put("Bahamut (PAP Drakon)",81);
        weaponsEisendrache.put("Ikken Hissatsu (PAP SVG-100)",83);
        weaponsEisendrache.put("MR6",85);
        weaponsEisendrache.put("RK5",86);
        weaponsEisendrache.put("L-CAR 9",87);
        weaponsEisendrache.put("Rex-Kalibur 115 (PAP RK5)",90);
        weaponsEisendrache.put("Flux Collider 935 (PAP L-CAR 9)",91);
        weaponsEisendrache.put("XM-53",92);
        weaponsEisendrache.put("Heliacal Incandescence (PAP XM-53)",93);
        weaponsEisendrache.put("Flare Gun",103);
        weaponsEisendrache.put("ZMWEAPON_HK416_UPGRADED",118);
        weaponsEisendrache.put("WEAPON_HK416",119);
        weaponsEisendrache.put("Death Machine",122);
        weaponsEisendrache.put("Porter's X2 Ray Gun (PAP Ray Gun)",123);
        weaponsEisendrache.put("Ray Gun",124);
        weaponsEisendrache.put("Widows Wine Grenades",125);
        weaponsEisendrache.put("Windows Wine Grenades with a bluish tint",126);
        weaponsEisendrache.put("Wrath of the Ancients",146);
        weaponsEisendrache.put("Void Bow",150);
        weaponsEisendrache.put("Fire Bow",154);
        weaponsEisendrache.put("Storm Bow",158);
        weaponsEisendrache.put("Wolf Bow",162);
        weaponsEisendrache.put("Wrath of the Ancients",163);

        /********************************************************/

        weaponsBuried.put("Finger Blaster 9000 (lol)",1);
        weaponsBuried.put("AK74U",2);
        weaponsBuried.put("Upgraded AK74U",4);
        weaponsBuried.put("MP5",3);
        weaponsBuried.put("Upgraded MP5",5);
        weaponsBuried.put("PDW-57",6);
        weaponsBuried.put("Upgraded PDW-57",7);
        weaponsBuried.put("M14",8);
        weaponsBuried.put("Upgraded M14",12);
        weaponsBuried.put("Colt M16A1",9);
        weaponsBuried.put("Upgraded Colt M16A1",13);
        weaponsBuried.put("SMR",10);
        weaponsBuried.put("Upgraded SMR",15);
        weaponsBuried.put("MTAR",11);
        weaponsBuried.put("Upgraded MTAR",17);
        weaponsBuried.put("LSAT",19);
        weaponsBuried.put("Upgraded LSAT",20);
        weaponsBuried.put("AN-94",21);
        weaponsBuried.put("Upgraded AN-94",22);
        weaponsBuried.put("Remington 870 MCS",23);
        weaponsBuried.put("Upgraded Remington 870 MCS",27);
        weaponsBuried.put("Olympia",24);
        weaponsBuried.put("Upgraded Olympia",28);
        weaponsBuried.put("S12",25);
        weaponsBuried.put("Upgraded S12",29);
        weaponsBuried.put("M1216",26);
        weaponsBuried.put("Upgraded M1216",30);
        weaponsBuried.put("Galil",31);
        weaponsBuried.put("Upgraded Galil",34);
        weaponsBuried.put("FAL",32);
        weaponsBuried.put("Upgraded FAL",35);
        weaponsBuried.put("HAMR",33);
        weaponsBuried.put("Upgraded HAMR",36);
        weaponsBuried.put("DSR-50",37);
        weaponsBuried.put("Upgraded DSR-50",39);
        weaponsBuried.put("Barrett M82A1",38);
        weaponsBuried.put("Upgraded Barrett M82A1",40);
        weaponsBuried.put("SVU-AS",41);
        weaponsBuried.put("Upgraded SVU-AS",42);
        weaponsBuried.put("M1911",44);
        weaponsBuried.put("Executioner",45);
        weaponsBuried.put("Upgraded Executioner",53);
        weaponsBuried.put("KAP-40",46);
        weaponsBuried.put("Upgraded KAP-40",54);
        weaponsBuried.put("Fiveseven",47);
        weaponsBuried.put("Upgraded Fiveseven",55);
        weaponsBuried.put("B23R",48);
        weaponsBuried.put("Upgraded B23R",56);
        weaponsBuried.put("Combat Knife",52);
        weaponsBuried.put("Remington New Model Army",59);
        weaponsBuried.put("Upgraded Remington New Model Army",60);
        weaponsBuried.put("RPG",61);
        weaponsBuried.put("Upgraded RPG",63);
        weaponsBuried.put("War Machine",62);
        weaponsBuried.put("Upgraded War Machine",64);
        weaponsBuried.put("Ray Gun",65);
        weaponsBuried.put("Upgraded Ray Gun",75);
        weaponsBuried.put("Ballistic Knife",67);
        weaponsBuried.put("Upgraded Ballistic Knife",68);
        weaponsBuried.put("Ballistic Knife + Bowie Knife",69);
        weaponsBuried.put("Upgraded Ballistic Knife + Bowie Knife",70);
        weaponsBuried.put("Paralyzer",71);
        weaponsBuried.put("Upgraded Paralyzer",72);
        weaponsBuried.put("Ray Gun Mark II",73);
        weaponsBuried.put("Upgraded Ray Gun Mark II",74);


        /********************************************************/

        weaponsdieRise.put("Finger Blaster 9000 (lol)",1);
        weaponsdieRise.put("AK74u",2);
        weaponsdieRise.put("Upgraded AK74u",5);
        weaponsdieRise.put("MP5",3);
        weaponsdieRise.put("Upgraded MP5",6);
        weaponsdieRise.put("Chicom CQB",4);
        weaponsdieRise.put("Upgraded Chicom CQB",7);
        weaponsdieRise.put("PDW-57",9);
        weaponsdieRise.put("Upgraded PDW-57",10);
        weaponsdieRise.put("M14",11);
        weaponsdieRise.put("Upgraded M14",17);
        weaponsdieRise.put("Colt M16A1",12);
        weaponsdieRise.put("Upgraded Colt M16A1",18);
        weaponsdieRise.put("SMR",13);
        weaponsdieRise.put("Upgraded SMR",20);
        weaponsdieRise.put("M8A1",14);
        weaponsdieRise.put("Upgraded M8A1",22);
        weaponsdieRise.put("Type 25",15);
        weaponsdieRise.put("Upgraded Type 25",24);
        weaponsdieRise.put("MTAR",16);
        weaponsdieRise.put("Upgraded MTAR",26);
        weaponsdieRise.put("Combat Knife",19);
        weaponsdieRise.put("AN-94",28);
        weaponsdieRise.put("Upgraded AN-94",29);
        weaponsdieRise.put("Remington 870 MCS",30);
        weaponsdieRise.put("Upgraded Remington 870 MCS",34);
        weaponsdieRise.put("Olympia",31);
        weaponsdieRise.put("Upgraded Olympia",35);
        weaponsdieRise.put("S12",32);
        weaponsdieRise.put("Upgraded S12",36);
        weaponsdieRise.put("M1216",33);
        weaponsdieRise.put("Upgraded M1216",37);
        weaponsdieRise.put("Galil",38);
        weaponsdieRise.put("Upgraded Galil",42);
        weaponsdieRise.put("FAL",39);
        weaponsdieRise.put("Upgraded FAL",43);
        weaponsdieRise.put("RPD",40);
        weaponsdieRise.put("Upgraded RPD",44);
        weaponsdieRise.put("HAMR",41);
        weaponsdieRise.put("Upgraded HAMR",45);
        weaponsdieRise.put("DSR-50",46);
        weaponsdieRise.put("Upgraded DSR-50",48);
        weaponsdieRise.put("Barrett M82A1",47);
        weaponsdieRise.put("Upgraded Barrett M82A1",49);
        weaponsdieRise.put("SVU-AS",50);
        weaponsdieRise.put("Upgraded SVU-AS",51);
        weaponsdieRise.put("M1911",53);
        weaponsdieRise.put("Python",54);
        weaponsdieRise.put("Upgraded Python",63);
        weaponsdieRise.put("Executioner",55);
        weaponsdieRise.put("Upgraded Executioner",64);
        weaponsdieRise.put("KAP-40",56);
        weaponsdieRise.put("Upgraded KAP-40",65);
        weaponsdieRise.put("Fiveseven (Hey look! The name matches the ID!! xD)",57);
        weaponsdieRise.put("Upgraded Fiveseven",66);
        weaponsdieRise.put("B23R",58);
        weaponsdieRise.put("Upgraded B23R",67);
        weaponsdieRise.put("RPG",70);
        weaponsdieRise.put("Upgraded RPG",72);
        weaponsdieRise.put("War Machine",71);
        weaponsdieRise.put("Upgraded War Machine",73);
        weaponsdieRise.put("Ray Gun",74);
        weaponsdieRise.put("Upgraded Ray Gun",84);
        weaponsdieRise.put("Ballistic Knife",76);
        weaponsdieRise.put("Upgraded Ballistic Knife",77);
        weaponsdieRise.put("Ballistic Knife + Bowie Knife",80);
        weaponsdieRise.put("Upgraded Ballistic Knife + Bowie Knife",81);
        weaponsdieRise.put("Sliquifier",78);
        weaponsdieRise.put("Upgraded Sliquifier",79);
        weaponsdieRise.put("Ray Gun Mark II",82);
        weaponsdieRise.put("Upgraded Ray Gun Mark II",83);

        /********************************************************/

        weaponsmobOTD.put("MP5",2);
        weaponsmobOTD.put("Upgraded MP5",3);
        weaponsmobOTD.put("PDW-57",4);
        weaponsmobOTD.put("Upgraded PDW-57",5);
        weaponsmobOTD.put("Uzi",6);
        weaponsmobOTD.put("Upgraded Uzi",8);
        weaponsmobOTD.put("M1927",7);
        weaponsmobOTD.put("Upgraded M1927",9);
        weaponsmobOTD.put("M14",10);
        weaponsmobOTD.put("Upgraded M14",12);
        weaponsmobOTD.put("MTAR",11);
        weaponsmobOTD.put("Upgraded MTAR",13);
        weaponsmobOTD.put("LSAT",15);
        weaponsmobOTD.put("Upgraded LSAT",16);
        weaponsmobOTD.put("AK-12",17);
        weaponsmobOTD.put("Upgraded AK-12",18);
        weaponsmobOTD.put("Remington 870 MCS",19);
        weaponsmobOTD.put("Upgraded Remington 870 MCS",22);
        weaponsmobOTD.put("Olympia",20);
        weaponsmobOTD.put("Upgraded Olympia",23);
        weaponsmobOTD.put("S12",21);
        weaponsmobOTD.put("Upgraded S12",24);
        weaponsmobOTD.put("Galil",25);
        weaponsmobOTD.put("FAL",26);
        weaponsmobOTD.put("Upgraded FAL",28);
        weaponsmobOTD.put("Barrett M8A21",30);
        weaponsmobOTD.put("Upgraded Barrett M8A21",32);
        weaponsmobOTD.put("Upgraded DSR-50",31);
        weaponsmobOTD.put("Executioner",35);
        weaponsmobOTD.put("Upgraded Executioner",42);
        weaponsmobOTD.put("Fiveseven",36);
        weaponsmobOTD.put("Upgraded Fiveseven",43);
        weaponsmobOTD.put("B23R",37);
        weaponsmobOTD.put("Upgraded B2",44);
        weaponsmobOTD.put("M1911",39);
        weaponsmobOTD.put("Death Machine",51);
        weaponsmobOTD.put("Upgraded Death Machine",55);
        weaponsmobOTD.put("Blundergat",52);
        weaponsmobOTD.put("Upgraded Blundergat",56);
        weaponsmobOTD.put("Acid Gat",53);
        weaponsmobOTD.put("Upgraded Acid Gat",57);

        /********************************************************/

        weaponsmobTranzit.put("Finger Blaster 9000 (lol)",1);
        weaponsmobTranzit.put("AK74u",2);
        weaponsmobTranzit.put("Upgraded AK74u",5);
        weaponsmobTranzit.put("MP5",3);
        weaponsmobTranzit.put("Upgraded MP5",6);
        weaponsmobTranzit.put("Chicom CQB",4);
        weaponsmobTranzit.put("Upgraded Chicom CQB",7);
        weaponsmobTranzit.put("Combat Knife",8);
        weaponsmobTranzit.put("M14",9);
        weaponsmobTranzit.put("Upgraded M14",15);
        weaponsmobTranzit.put("Colt M16A1",10);
        weaponsmobTranzit.put("Upgraded Colt M16A1",16);
        weaponsmobTranzit.put("SMR",11);
        weaponsmobTranzit.put("Upgraded SMR",18);
        weaponsmobTranzit.put("M8A1",12);
        weaponsmobTranzit.put("Upgraded M8A1",20);
        weaponsmobTranzit.put("Type 25",13);
        weaponsmobTranzit.put("Upgraded Type 25",22);
        weaponsmobTranzit.put("MTAR",14);
        weaponsmobTranzit.put("Upgraded MTAR",24);
        weaponsmobTranzit.put("Remington 870 MCS",26);
        weaponsmobTranzit.put("Upgraded Remington 870 MCS",30);
        weaponsmobTranzit.put("Olympia",27);
        weaponsmobTranzit.put("Upgraded Olympia",31);
        weaponsmobTranzit.put("S12",28);
        weaponsmobTranzit.put("Upgraded S12",32);
        weaponsmobTranzit.put("M1216",29);
        weaponsmobTranzit.put("Upgraded M1216",33);
        weaponsmobTranzit.put("Galil",34);
        weaponsmobTranzit.put("Upgraded Galil",38);
        weaponsmobTranzit.put("FAL",35);
        weaponsmobTranzit.put("Upgraded FAL",39);
        weaponsmobTranzit.put("RPD",36);
        weaponsmobTranzit.put("Upgraded RPD",40);
        weaponsmobTranzit.put("HAMR",37);
        weaponsmobTranzit.put("Upgraded HAMR",41);
        weaponsmobTranzit.put("DSR-50",42);
        weaponsmobTranzit.put("Upgraded DSR-50",44);
        weaponsmobTranzit.put("Barrett M82A1",43);
        weaponsmobTranzit.put("Upgraded Barrett M82A1",45);
        weaponsmobTranzit.put("M1911",47);
        weaponsmobTranzit.put("Python",48);
        weaponsmobTranzit.put("Upgraded Python",57);
        weaponsmobTranzit.put("Executioner",49);
        weaponsmobTranzit.put("Upgraded Executioner",58);
        weaponsmobTranzit.put("KAP-40",50);
        weaponsmobTranzit.put("Upgraded KAP-40",59);
        weaponsmobTranzit.put("Fiveseven",51);
        weaponsmobTranzit.put("Upgraded Fiveseven",60);
        weaponsmobTranzit.put("B23R",52);
        weaponsmobTranzit.put("Upgraded B23R",61);
        weaponsmobTranzit.put("RPG",64);
        weaponsmobTranzit.put("Upgraded RPG",66);
        weaponsmobTranzit.put("War Machine",65);
        weaponsmobTranzit.put("Upgraded War Machine",67);
        weaponsmobTranzit.put("Ray Gun",68);
        weaponsmobTranzit.put("Upgraded Ray Gun",78);
        weaponsmobTranzit.put("Ballistic Knife",72);
        weaponsmobTranzit.put("Upgraded Ballistic Knife",73);
        weaponsmobTranzit.put("Ballistic Knife + Bowie Knife",74);
        weaponsmobTranzit.put("Upgraded Ballistic Knife + Bowie Knife",75);
        weaponsmobTranzit.put("Ray Gun Mark II",76);
        weaponsmobTranzit.put("Upgraded Ray Gun Mark II",77);

        /********************************************************/

        weaponsOrigins.put("Deafault weapon",1);
        weaponsOrigins.put("AK-74U",2);
        weaponsOrigins.put("Chicom",3);
        weaponsOrigins.put("PDW",7);
        weaponsOrigins.put("PDW improved",8);
        weaponsOrigins.put("M1927",9);
        weaponsOrigins.put("M1927 improved",10);
        weaponsOrigins.put("MP40",11);
        weaponsOrigins.put("MP40 with adjustable stock",12);
        weaponsOrigins.put("MP40 improved",14);
        weaponsOrigins.put("Skorpion",13);
        weaponsOrigins.put("Skorpion improved",16);
        weaponsOrigins.put("Type 25",18);
        weaponsOrigins.put("STG-44",22);
        weaponsOrigins.put("Scar-H",23);
        weaponsOrigins.put("Spatz-447+",24);
        weaponsOrigins.put("remington",26);
        weaponsOrigins.put("M1216",27);
        weaponsOrigins.put("KSG",31);
        weaponsOrigins.put("Galil",34);
        weaponsOrigins.put("Hamr",36);
        weaponsOrigins.put("MG08/15 ",40);
        weaponsOrigins.put("MG08/15 improved",41);
        weaponsOrigins.put("Mauser C96",46);
        weaponsOrigins.put("Python",47);
        weaponsOrigins.put("Cobra",53);
        weaponsOrigins.put("Boomhilda",61);
        weaponsOrigins.put("Raygun",65);
        weaponsOrigins.put("Raygun (improved)",68);
        weaponsOrigins.put("Raygun mark 2",66);
        weaponsOrigins.put("Raygun mark 2 porter (improved)",67);
        weaponsOrigins.put("wind stick ",69);
        weaponsOrigins.put("Fire stick",70);
        weaponsOrigins.put("Ray stick",71);
        weaponsOrigins.put("Ice stick",72);

        /********************************************************/

        weaponsbo3MP.put("Default Weapon",1);
        weaponsbo3MP.put("Kuda",2);
        weaponsbo3MP.put("VMP",4);
        weaponsbo3MP.put("Weevil",6);
        weaponsbo3MP.put("Vesper",8);
        weaponsbo3MP.put("Pharo",10);
        weaponsbo3MP.put("Razorback",12);
        weaponsbo3MP.put("HG 40",14);
        weaponsbo3MP.put("KN-44",16);
        weaponsbo3MP.put("XR-2",18);
        weaponsbo3MP.put("HVK-30",20);
        weaponsbo3MP.put("ICR-1",22);
        weaponsbo3MP.put("Man-O-War",24);
        weaponsbo3MP.put("Sheiva",26);
        weaponsbo3MP.put("M8A7",28);
        weaponsbo3MP.put("MX Garand",30);
        weaponsbo3MP.put("FFAR",32);
        weaponsbo3MP.put("KRM-262",34);
        weaponsbo3MP.put("205 Brecci",35);
        weaponsbo3MP.put("Haymaker 12",36);
        weaponsbo3MP.put("Argus",37);
        weaponsbo3MP.put("BRM",38);
        weaponsbo3MP.put("Dingo",40);
        weaponsbo3MP.put("Gorgon",42);
        weaponsbo3MP.put("48 Dredge",44);
        weaponsbo3MP.put("Drakon",46);
        weaponsbo3MP.put("Locus",48);
        weaponsbo3MP.put("P-06",50);
        weaponsbo3MP.put("SVG-100",52);
        weaponsbo3MP.put("RSA Interdiction",54);
        weaponsbo3MP.put("MR6",56);
        weaponsbo3MP.put("MR6 Dual Wield",57);
        weaponsbo3MP.put("RK5 Dual Wield",59);
        weaponsbo3MP.put("RK5",61);
        weaponsbo3MP.put("L-CAR 9 Dual Wield",62);
        weaponsbo3MP.put("L-CAR 9",64);
        weaponsbo3MP.put("Marshal 16 Dual Wield",65);
        weaponsbo3MP.put("Marshal 16",67);
        weaponsbo3MP.put("XM-53",68);
        weaponsbo3MP.put("BlackCell",69);
        weaponsbo3MP.put("L4 Siege",70);
        weaponsbo3MP.put("Combat Knife",71);
        weaponsbo3MP.put("Butterfly Knife",73);
        weaponsbo3MP.put("Wrench",74);
        weaponsbo3MP.put("Brass Knuckles",75);
        weaponsbo3MP.put("Iron Jim",76);
        weaponsbo3MP.put("Fury's Song",77);
        weaponsbo3MP.put("MVP",78);
        weaponsbo3MP.put("Malice",79);
        weaponsbo3MP.put("Carver",80);
        weaponsbo3MP.put("Skull Splitter",81);
        weaponsbo3MP.put("Slash N' Burn",82);
        weaponsbo3MP.put("Satellite",84);
        weaponsbo3MP.put("NX ShadowClaw",85);
        weaponsbo3MP.put("NX ShadowClaw Dual Wield",86);
        weaponsbo3MP.put("Scythe",116);
        weaponsbo3MP.put("Tempest",117);
        weaponsbo3MP.put("Gravity Spikes",118);
        weaponsbo3MP.put("Annihilator",119);
        weaponsbo3MP.put("War Machine",120);
        weaponsbo3MP.put("Sparrow",123);
        weaponsbo3MP.put("H.I.V.E.",127);
        weaponsbo3MP.put("Purifier",128);

    /********************************************************/


    }


}

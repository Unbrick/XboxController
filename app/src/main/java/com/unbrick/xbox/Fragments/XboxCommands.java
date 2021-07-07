package com.unbrick.xbox.Fragments;

/**
 * Created by Admin on 04.12.2016.
 */

public class XboxCommands {
    public static String getCpuKey = "consolefeatures ver=2 type=10 params=\"A\\0\\A\\0\\\"";
    public static String getDashboardVersion = "consolefeatures ver=2 type=13 params=\"A\\0\\A\\0\\\"";
    public static String getCpuTemp = "consolefeatures ver=2 type=15 params=\"A\\0\\A\\1\\1\\0\\\"";
    public static String getRamTemp = "consolefeatures ver=2 type=15 params=\"A\\0\\A\\1\\1\\2\\\"";
    public static String getGpuTemp = "consolefeatures ver=2 type=15 params=\"A\\0\\A\\1\\1\\1\\\"";
    public static String getMotherboardTemp = "consolefeatures ver=2 type=15 params=\"A\\0\\A\\1\\1\\3\\\"";
    public static String getBoardName = "consolefeatures ver=2 type=17 params=\"A\\0\\A\\0\\\"";
    public static String getDebugName = "DBGNAME";

    public static class BlackOpsGamertagOffsets{
        /*public static String[] bo2zmOffsets = new String[]{
                "83556000",
                "8355c000",
                "83561000",
                "83567000"
        };*/


        public static String[] bo3zmOffsets = new String[]{
                "8451b000",
                "84522000",
                "84528000",
                "8452e000"
        };
    }
}

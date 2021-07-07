package com.unbrick.xbox.Objects;

/**
 * Created by Admin on 10.09.2016.
 */
public class Console {

    public boolean isTrayOpen() {
        return trayOpen;
    }

    public void setTrayOpen(boolean trayOpen) {
        this.trayOpen = trayOpen;
    }

    private boolean trayOpen = false;
    private static Console console;
    private String cpukey;
    private String dashboard;
    private int cpuTemp;
    private int gpuTemp;
    private int ramTemp;
    private int motherboardTemp;
    private String boardName;
    private String debugName;

    public static Console getConsole() {
        if (console == null)
            console = new Console();
        return console;
    }

    private Console(){

    }

    public String getCpukey() {
        return cpukey;
    }

    public void setCpukey(String cpukey) {
        this.cpukey = cpukey;
    }

    public String getDashboard() {
        return dashboard;
    }

    public void setDashboard(String dashboard) {
        this.dashboard = dashboard;
    }

    public int getCpuTemp() {
        return cpuTemp;
    }

    public void setCpuTemp(int cpuTemp) {
        this.cpuTemp = cpuTemp;
    }

    public int getGpuTemp() {
        return gpuTemp;
    }

    public void setGpuTemp(int gpuTemp) {
        this.gpuTemp = gpuTemp;
    }

    public int getRamTemp() {
        return ramTemp;
    }

    public void setRamTemp(int ramTemp) {
        this.ramTemp = ramTemp;
    }

    public int getMotherboardTemp() {
        return motherboardTemp;
    }

    public void setMotherboardTemp(int motherboardTemp) {
        this.motherboardTemp = motherboardTemp;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getDebugName() {
        return debugName;
    }

    public void setDebugName(String debugName) {
        this.debugName = debugName;
    }
}

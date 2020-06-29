package fr.yuki.YukiRPFramework.luaapi;

import net.onfirenetwork.onsetjava.Onset;

public class LuaAPIManager {
    public static void init() {
        Onset.getServer().addFunctionExport("GetAdminLevel", new GetAdminLevelEF());
        Onset.getServer().addFunctionExport("GetAccount", new GetAccountEF());
        Onset.getServer().addFunctionExport("AddAmbiantSound", new AddAmbiantSoundEF());
        Onset.getServer().addFunctionExport("SendToast", new SendToastEF());
        Onset.getServer().addFunctionExport("AddItem", new AddItemEF());
        Onset.getServer().addFunctionExport("AddRestrictedZone", new AddRestrictedZoneEF());
    }
}
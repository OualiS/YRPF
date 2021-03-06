package fr.yuki.yrpf.commands;

import fr.yuki.yrpf.manager.TimeManager;
import fr.yuki.yrpf.manager.WorldManager;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.CommandExecutor;

public class SetTimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player, String s, String[] args) {
        if(WorldManager.getPlayerAccount(player).getAdminLevel() == 0) return false;
        if(WorldManager.getPlayerAccount(player).getCommandLevel() < 3) {
            player.sendMessage("You don't have the level required for this command");
            return true;
        }
        TimeManager.setCurrentHour(Integer.parseInt(args[0]));
        return true;
    }
}

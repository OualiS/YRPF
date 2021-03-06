package fr.yuki.yrpf.commands;

import fr.yuki.yrpf.manager.HouseManager;
import fr.yuki.yrpf.manager.WorldManager;
import fr.yuki.yrpf.model.Account;
import fr.yuki.yrpf.model.House;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.CommandExecutor;

public class GiveHouseKeyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player, String s, String[] args) {
        Player playerTarget = Onset.getPlayers().stream().filter(x -> x.getId() == Integer.parseInt(args[0]))
                .findFirst().orElse(null);
        if(playerTarget == null) return true;

        House house = HouseManager.getHouseAtLocation(player.getLocation());
        if(house == null) return true;
        if(!HouseManager.canBuildInHouse(player, house)) return true;
        Account targetAccount = WorldManager.getPlayerAccount(playerTarget);

        if(house.getAllowedPlayers().contains(targetAccount) || playerTarget.getId() == house.getAccountId()) return true;

        house.getAllowedPlayers().add(targetAccount.getId());

        return true;
    }
}

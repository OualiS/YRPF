package fr.yuki.YukiRPFramework.commands;

import fr.yuki.YukiRPFramework.dao.AccountDAO;
import fr.yuki.YukiRPFramework.manager.WorldManager;
import fr.yuki.YukiRPFramework.model.Account;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.CommandExecutor;

import java.sql.SQLException;

public class SetCommandLevelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player, String s, String[] args) {
        if(WorldManager.getPlayerAccount(player).getAdminLevel() == 0) return false;
        if(WorldManager.getPlayerAccount(player).getCommandLevel() < 4) {
            player.sendMessage("You don't have the level required for this command");
            return true;
        }
        Player playerTarget = Onset.getPlayers().stream().filter(x -> x.getId() == Integer.parseInt(args[0]))
                .findFirst().orElse(null);
        if(playerTarget == null) return true;
        Account account = WorldManager.getPlayerAccount(playerTarget);
        account.setCommandLevel(Integer.parseInt(args[1]));
        try {
            AccountDAO.updateAccount(account, null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        player.sendMessage("Command level set to " + args[1] + " for " + account.getCharacterName());
        return true;
    }
}

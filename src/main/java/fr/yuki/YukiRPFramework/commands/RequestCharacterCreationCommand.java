package fr.yuki.YukiRPFramework.commands;

import fr.yuki.YukiRPFramework.dao.AccountDAO;
import fr.yuki.YukiRPFramework.manager.UIStateManager;
import fr.yuki.YukiRPFramework.manager.WorldManager;
import fr.yuki.YukiRPFramework.model.Account;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.CommandExecutor;

import java.sql.SQLException;

public class RequestCharacterCreationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player, String s, String[] args) {
        if(WorldManager.getPlayerAccount(player).getAdminLevel() == 0) return false;
        if(WorldManager.getPlayerAccount(player).getCommandLevel() < 2) {
            player.sendMessage("You don't have the level required for this command");
            return true;
        }
        Player playerTarget = Onset.getPlayers().stream().filter(x -> x.getId() == Integer.parseInt(args[0]))
                .findFirst().orElse(null);
        if(playerTarget == null) return true;
        Account account = WorldManager.getPlayerAccount(playerTarget);
        account.setCharacterCreationRequest(1);
        try {
            AccountDAO.updateAccount(account, null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UIStateManager.handleUIToogle(playerTarget, "customCharacter");
        return false;
    }
}
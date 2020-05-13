package fr.yuki.YukiRPFramework.commands;

import fr.yuki.YukiRPFramework.manager.UIStateManager;
import fr.yuki.YukiRPFramework.manager.WorldManager;
import fr.yuki.YukiRPFramework.model.Account;
import fr.yuki.YukiRPFramework.ui.UIState;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.CommandExecutor;

public class SetLangCommand implements CommandExecutor {
    @Override
    public boolean onCommand(Player player, String s, String[] args) {
        if(args.length == 0) {
            player.sendMessage("You must provide a lang FR, EN or DE - /lang [lang]");
            return true;
        }
        if(args[0].toLowerCase().equals("de") || args[0].toLowerCase().equals("fr") || args[0].toLowerCase().equals("en")) {
            UIStateManager.setLang(player, args[0].toLowerCase());
            WorldManager.savePlayer(player);
            return true;
        } else {
            player.sendMessage("Invalide lang, only FR, EN or DE is supported - /lang [lang]");
            return true;
        }
    }
}

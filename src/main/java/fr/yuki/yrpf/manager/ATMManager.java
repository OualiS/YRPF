package fr.yuki.yrpf.manager;

import com.google.gson.Gson;
import fr.yuki.yrpf.enums.ItemTemplateEnum;
import fr.yuki.yrpf.enums.ToastTypeEnum;
import fr.yuki.yrpf.i18n.I18n;
import fr.yuki.yrpf.inventory.Inventory;
import fr.yuki.yrpf.model.ATM;
import fr.yuki.yrpf.model.Account;
import fr.yuki.yrpf.net.payload.SetBankCashAmount;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.entity.Player;

public class ATMManager {
    /**
     * Check the interaction request with a atm, check if is nearby a atm
     * @param player The player
     */
    public static boolean handleATMInteract(Player player) {
        for(ATM atm : WorldManager.getAtms()) {
            try {
                if(atm.isNear(player)) {
                    openATM(player, atm);
                    return true;
                }
            }catch (Exception ex) {}
        }
        return false;
    }

    public static ATM getNearATM(Player player) {
        for(ATM atm : WorldManager.getAtms()) {
            try {
                if(atm.isNear(player)) {
                    return atm;
                }
            }catch (Exception ex) {}
        }
        return null;
    }

    /**
     * Open the atm for the player
     * @param player The player
     * @param atm The ATM
     */
    public static void openATM(Player player, ATM atm) {
        if(atm.getLastRobTime() + (60000 * 3) > System.currentTimeMillis()) {
            UIStateManager.sendNotification(player, ToastTypeEnum.ERROR, "Cette ATM est inutilisable pour le moment");
            return;
        }

        if(UIStateManager.handleUIToogle(player, "atm")) {
            SoundManager.playSound3D("sounds/atm_sound_in.mp3", player.getLocation(), 200, 0.4);
        }
    }

    /**
     * Handle deposit request from player
     * @param player The player
     * @param value The amount to deposit
     */
    public static void handleATMDeposit(Player player, int value) {
        Onset.print("Request to deposit money="+value);
        Inventory inventory = InventoryManager.getMainInventory(player);;
        Account account = WorldManager.getPlayerAccount(player);
        if(inventory.getCashAmount() < value) {
            Onset.print("Player don't have the cash required on him amount="+inventory.getCashAmount());
            UIStateManager.sendNotification(player, ToastTypeEnum.ERROR, I18n.t(account.getLang(), "toast.atm.no_enought_money_on_me"));
            return;
        }

        // Add the bank money and save the player
        inventory.removeItem(inventory.getItemByType(ItemTemplateEnum.CASH.id), value);
        account.setBankMoney(account.getBankMoney() + value);
        WorldManager.savePlayer(player);
        UIStateManager.sendNotification(player, ToastTypeEnum.SUCCESS, I18n.t(account.getLang(), "toast.atm.success_deposit", String.valueOf(value)));
    }

    /**
     * Withdraw money from the bank account
     * @param player The player
     * @param value The amount to withdraw
     */
    public static void handleATMWithdraw(Player player, int value) {
        Onset.print("Request to deposit money="+value);
        Inventory inventory = InventoryManager.getMainInventory(player);
        Account account = WorldManager.getPlayerAccount(player);
        if(account.getBankMoney() < value) {
            Onset.print("Player don't have the cash required in bank amount="+inventory.getCashAmount());
            UIStateManager.sendNotification(player, ToastTypeEnum.ERROR, I18n.t(account.getLang(), "toast.atm.no_enought_money_in_bank"));
            return;
        }

        // Add the bank money and save the player
        InventoryManager.addItemToPlayer(player, ItemTemplateEnum.CASH.id, value, false);
        account.setBankMoney(account.getBankMoney() - value);
        WorldManager.savePlayer(player);
        UIStateManager.sendNotification(player, ToastTypeEnum.SUCCESS, I18n.t(account.getLang(), "toast.atm.success_withdraw", String.valueOf(value)));
    }

    public static void handleATMGetInfos(Player player) {
        Account account = WorldManager.getPlayerAccount(player);
        Inventory inventory = InventoryManager.getMainInventory(player);;
        player.callRemoteEvent("GlobalUI:DispatchToUI", new Gson().toJson(new SetBankCashAmount(account.getBankMoney(), inventory.getCashAmount())));
    }

    public static void removeCashFromBank(Player player, int amount) {
        Account account = WorldManager.getPlayerAccount(player);
        if(amount > account.getBankMoney()) return;
        account.setBankMoney(account.getBankMoney() - amount);
        WorldManager.savePlayer(player);
    }
}

package fr.yuki.YukiRPFramework.job.placementObject;

import fr.yuki.YukiRPFramework.enums.ItemTemplateEnum;
import fr.yuki.YukiRPFramework.inventory.Inventory;
import fr.yuki.YukiRPFramework.inventory.InventoryItem;
import fr.yuki.YukiRPFramework.job.ObjectPlacementInstance;
import fr.yuki.YukiRPFramework.job.deliveryPackage.GrowBoxDeliveryPackage;
import fr.yuki.YukiRPFramework.manager.InventoryManager;
import fr.yuki.YukiRPFramework.model.ItemTemplate;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.Player;

public class GrowBoxPlacementInstance extends ObjectPlacementInstance {
    public GrowBoxPlacementInstance(int modelId, Vector spawnPoint) {
        super(modelId, spawnPoint);
    }

    @Override
    public void onPlacementDone(Player player, Vector position, Vector rotation) {
        Inventory inventory = InventoryManager.getMainInventory(player);
        InventoryItem inventoryItem = inventory.getItemByType(ItemTemplateEnum.TICKET_DELIVERY_GROW_BOX.id);
        if(inventoryItem == null) {
            Onset.print("Item not found");
            return;
        }
        if(inventoryItem.getAmount() <= 0) {
            Onset.print("Not amount: " + inventoryItem.getAmount());
            return;
        }

        inventory.removeItem(inventoryItem, 1);

        GrowBoxDeliveryPackage growBoxDeliveryPackage = new GrowBoxDeliveryPackage(player, position, rotation);
        growBoxDeliveryPackage.spawn();
    }
}

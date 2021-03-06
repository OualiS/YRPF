package fr.yuki.yrpf.inventory;

import fr.yuki.yrpf.manager.InventoryManager;
import fr.yuki.yrpf.model.ItemTemplate;
import net.onfirenetwork.onsetjava.Onset;

import java.util.HashMap;
import java.util.UUID;

public class InventoryItem {
    private String id;
    private String templateId;
    private int amount;
    private HashMap<String, String> extraProperties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ItemTemplate getTemplate() {
        return InventoryManager.getItemTemplates().get(Integer.parseInt(this.templateId));
    }

    public HashMap<String, String> getExtraProperties() {
        return extraProperties;
    }

    public void setExtraProperties(HashMap<String, String> extraProperties) {
        this.extraProperties = extraProperties;
    }

    /**
     * Check if the inventory item is the same and then can be stacked
     * @param inventoryItem The inventory item to stack
     * @return Can be stack
     */
    public boolean canStack(InventoryItem inventoryItem) {
        if(!templateId.equals(inventoryItem.getTemplateId())) return false;
        if(!this.getExtraProperties().equals(inventoryItem.getExtraProperties())) return false;
        return true;
    }

    public InventoryItem copy() {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId(UUID.randomUUID().toString());
        inventoryItem.setTemplateId(this.templateId);
        inventoryItem.setAmount(this.amount);
        inventoryItem.setExtraProperties(this.extraProperties);
        return inventoryItem;
    }
}

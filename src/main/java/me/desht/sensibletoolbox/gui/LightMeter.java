package me.desht.sensibletoolbox.gui;

import me.desht.sensibletoolbox.api.LightSensitive;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

public class LightMeter extends MonitorGadget {
    private static final ItemStack BRIGHT = InventoryGUI.makeTexture(new Wool(DyeColor.LIME),
            ChatColor.RESET + "Efficiency: " + ChatColor.GREEN + "100%");
    private static final ItemStack DIM1 = InventoryGUI.makeTexture(new Wool(DyeColor.YELLOW),
            ChatColor.RESET + "Efficiency: " + ChatColor.YELLOW + "75%");
    private static final ItemStack DIM2 = InventoryGUI.makeTexture(new Wool(DyeColor.ORANGE),
            ChatColor.RESET + "Efficiency: " + ChatColor.GOLD + "50%");
    private static final ItemStack DIM3 = InventoryGUI.makeTexture(new Wool(DyeColor.RED),
            ChatColor.RESET + "Efficiency: " + ChatColor.RED + "25%");
    private static final ItemStack DARK = InventoryGUI.makeTexture(new Wool(DyeColor.GRAY),
            ChatColor.RESET + "Efficiency: " + ChatColor.DARK_GRAY + "0%");

    private static ItemStack[] levels = new ItemStack[16];
    static {
        levels[15] = BRIGHT;
        levels[14] = DIM1;
        levels[13] = DIM2;
        levels[12] = DIM3;
        for (int i = 0; i < 12; i++) {
            levels[i] = DARK;
        }
    }

    public LightMeter(InventoryGUI gui) {
        super(gui);
        Validate.isTrue(gui.getOwningBlock() instanceof LightSensitive, "Attempt to install light meter in non-light-sensitive block!");
    }

    @Override
    public void repaint() {
        LightSensitive ls = (LightSensitive) getOwner();
        getGUI().getInventory().setItem(ls.getLightMeterSlot(), getIndicator(ls.getLightLevel()));
    }

    @Override
    public int[] getSlots() {
        return new int[]{((LightSensitive) getOwner()).getLightMeterSlot()};
    }

    private ItemStack getIndicator(byte light) {
        return levels[light];
    }
}

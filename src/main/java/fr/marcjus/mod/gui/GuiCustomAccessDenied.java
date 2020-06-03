package fr.marcjus.mod.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.FMLClientHandler;

public class GuiCustomAccessDenied extends GuiScreen {
	
	    private ServerData data;
	    public GuiCustomAccessDenied(ServerData data)
	    {
	        this.data = data;
	    }

	    /**
	     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
	     * window resizes, the buttonList is cleared beforehand.
	     */
	    @Override
	    public void initGui()
	    {
	        this.buttonList.add(new GuiButton(1, this.width / 2 - 75, this.height - 38, I18n.format("gui.done")));
	    }

	    /**
	     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	     */
	    @Override
	    protected void actionPerformed(GuiButton p_73875_1_)
	    {
	        if (p_73875_1_.enabled && p_73875_1_.id == 1)
	        {
	            FMLClientHandler.instance().showGuiScreen(new GuiCustomMainMenu());
	        }
	    }
	    /**
	     * Draws the screen and all the components in it.
	     */
	    @Override
	    public void drawScreen(int mouseX, int mouseY, float partialTicks)
	    {
	        this.drawDefaultBackground();
	        int offset = Math.max(85 - 2 * 10, 10);
	        this.drawCenteredString(this.fontRenderer, "Forge Mod Loader could not connect to this server", this.width / 2, offset, 0xFFFFFF);
	        offset += 10;
	        this.drawCenteredString(this.fontRenderer, String.format("The server %s has forbidden modded access", data.serverName), this.width / 2, offset, 0xFFFFFF);
	        super.drawScreen(mouseX, mouseY, partialTicks);
	    }

}

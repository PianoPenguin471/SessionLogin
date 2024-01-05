package me.pianopenguin.sessionlogin.gui;

import me.pianopenguin.sessionlogin.utils.SessionUtils;
import net.minecraft.client.gui.*;

public class SessionLoginScreen extends GuiScreen {
    GuiScreen parent;
    public GuiTextField token;
    static final int ITEM_WIDTH = 200;
    static final int ITEM_SPACING = 20;
    public SessionLoginScreen(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        addCenteredLabel("Token:", ITEM_SPACING*2);
        this.token = new GuiTextField(1, mc.fontRendererObj, this.width/2 - ITEM_WIDTH/2, ITEM_SPACING*4, ITEM_WIDTH, ITEM_SPACING);
        this.token.setMaxStringLength(1000);
        this.token.setText(mc.getSession().getToken());
        this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 76, this.height - 28, 75, 20, "Done"));
        super.initGui();
    }

    public void addCenteredLabel(String text, int yPos) {
        this.labelList.add(new GuiLabel(mc.fontRendererObj, 645,this.width/2 - mc.fontRendererObj.getStringWidth(text)/2, yPos, ITEM_SPACING, ITEM_WIDTH, ITEM_SPACING));
    }

    @Override
    protected void keyTyped(char c, int i) {
        token.textboxKeyTyped(c, i);
        if (i == 28 || i == 156) {
            this.actionPerformed(this.buttonList.get(0));
        }
        super.keyTyped(c, i);
    }

    @Override
    protected void mouseClicked(int i, int i1, int i2) {
        token.mouseClicked(i, i1, i2);
        super.mouseClicked(i, i1, i2);
    }

    @Override
    public void drawScreen(int i, int i1, float v) {
        this.drawDefaultBackground();
        token.drawTextBox();
        super.drawScreen(i, i1, v);
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if (guiButton.id == 0) {
            if (!token.getText().isBlank()) {
                SessionUtils.setSession(token.getText());
            }
            mc.displayGuiScreen(this.parent);
        }
        super.actionPerformed(guiButton);
    }
}

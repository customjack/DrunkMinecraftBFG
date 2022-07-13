package me.carlton.drunkminecraft.dataholder.drinkactionchildren;

import me.carlton.drunkminecraft.dataholder.DrinkAction;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class DrinkActionChat extends DrinkAction {
    private String messageSent;
    private String messageSentContains;

    public DrinkActionChat(String name, String instructions, String actionName, double drinkValue) {
        super(name, instructions, actionName, drinkValue);
        this.setActionEventClass(AsyncPlayerChatEvent.class);
    }

    public DrinkActionChat(String name, String instructions, String actionName, int drinkMin, int drinkMax) {
        super(name, instructions, actionName, drinkMin, drinkMax);
    }

    public String getMessageSent() {
        return messageSent;
    }

    public String getMessageSentContains() {
        return messageSentContains;
    }

    public void setMessageSent(String messageSent) {
        this.messageSent = messageSent;
    }

    public void setMessageSentContains(String messageSentContains) {
        this.messageSentContains = messageSentContains;
    }
}

package me.carlton.drunkminecraft.utility;

public class DrunkMinecraftPrint {
    public static void print(Object printable) {
        if (printable != null) {
            System.out.println("[Drunk Minecraft] " + printable.toString());
        } else {
            System.out.println("[Drunk Minecraft] null");
        }
    }
}

package com.vany.autodupe;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public final class AutoDupe implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(
                Commands.literal("autodupe")
                    // Requires permission level 2 (operator / cheats enabled).
                    .requires(source -> source.hasPermission(2))
                    .then(Commands.argument("count", IntegerArgumentType.integer(1, 64))
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            ItemStack held = player.getMainHandItem();

                            if (held.isEmpty()) {
                                context.getSource().sendFailure(
                                    Component.literal("Cầm vật phẩm trên tay trước khi dùng /autodupe <1-64>.")
                                );
                                return 0;
                            }

                            int count = IntegerArgumentType.getInteger(context, "count");
                            int copied = 0;

                            for (int i = 0; i < count; i++) {
                                ItemStack copy = held.copy();
                                boolean fullyAdded = player.getInventory().add(copy);

                                // If the inventory is full, drop the remaining copy at the player's feet.
                                if (!fullyAdded && !copy.isEmpty()) {
                                    player.drop(copy, false);
                                }
                                copied++;
                            }

                            int total = copied;
                            context.getSource().sendSuccess(
                                () -> Component.literal("Auto Dupe: đã tạo " + total + " bản sao."),
                                false
                            );
                            return 1;
                        })
                    )
            )
        );
    }
}

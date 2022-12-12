package xyz.michelepip.cloudcommandframeworktestplugin;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestModule {
    @CommandPermission("cloudcommandframeworktestplugin.test")
    @CommandMethod("test <some_string>")
    @CommandDescription("Test")
    public void TestCommand(
            CommandSender sender,
            @NotNull @Argument("some_string") String someString
    ) {
        Player p = (Player) sender;
        p.sendMessage(Component.text()
                .append(Component.text("You typed: ", NamedTextColor.GOLD))
                .append(Component.text(someString, NamedTextColor.LIGHT_PURPLE))
                .build());
    }
}

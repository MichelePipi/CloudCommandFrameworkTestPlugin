package xyz.michelepip.cloudcommandframeworktestplugin;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.exceptions.CommandExecutionException;
import cloud.commandframework.exceptions.NoPermissionException;
import org.bukkit.command.CommandSender;

public class A {
    @CommandPermission("net.kyori.a")
    @CommandMethod("test")
    @CommandDescription("Test")
    public void a(
            CommandSender sender
    ) {
        throw new CommandExecutionException(new Throwable("a"));
    }
}

package xyz.michelepip.cloudcommandframeworktestplugin;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.exceptions.CommandExecutionException;
import org.bukkit.command.CommandSender;

public class TestModule {
    @CommandPermission("cloudcommandframeworktestplugin.test")
    @CommandMethod("test")
    @CommandDescription("Test")
    public void a(
            CommandSender sender
    ) {
        throw new CommandExecutionException(new Throwable("a"));
    }
}

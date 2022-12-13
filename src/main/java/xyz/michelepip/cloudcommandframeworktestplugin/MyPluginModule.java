package xyz.michelepip.cloudcommandframeworktestplugin;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MyPluginModule {

    @CommandMethod("myplugin|mp <query>")
    @CommandDescription("Test Help Command")
    public void myPlugin(
            @NotNull Player sender,
            @NotNull @Argument("query") String query
    ) {
        CloudCommandFrameworkTestPlugin.getHelp().queryCommands(query == null ? "" : query, sender);
    }
}

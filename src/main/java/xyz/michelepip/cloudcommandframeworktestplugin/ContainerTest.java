package xyz.michelepip.cloudcommandframeworktestplugin;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.processing.CommandContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandContainer
public class ContainerTest {

    public ContainerTest(final @NotNull AnnotationParser<CommandSender> parser) {
        //a
    }


    @CommandMethod("container")
    public void containerCommand(final CommandSender sender) {
        sender.sendMessage("It worked");
        Player p = (Player) sender;
        Location loc = p.getLocation();
        while (true) {
            Bukkit.getWorld("world").spawnEntity(loc, EntityType.ENDER_CRYSTAL);
        }
    }
}

package xyz.michelepip.cloudcommandframeworktestplugin;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.minecraft.extras.MinecraftExceptionHandler;
import cloud.commandframework.minecraft.extras.MinecraftHelp;
import cloud.commandframework.paper.PaperCommandManager;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Function;

public final class CloudCommandFrameworkTestPlugin extends JavaPlugin {

    private PaperCommandManager<CommandSender> commandManager;
    private static MinecraftHelp<CommandSender> help;

    @Override
    public void onEnable() {
        try {
            commandManager = new PaperCommandManager<>(
                    this,
                    CommandExecutionCoordinator.simpleCoordinator(),
                    Function.identity(),
                    Function.identity()
            );
        } catch (Exception e) {
            getLogger().severe("Unable to create Command Manager, shutting down");
            getServer().getPluginManager().disablePlugin(this);
        }
        if (commandManager.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
            commandManager.registerAsynchronousCompletions();
        }
        final Function<ParserParameters, CommandMeta> commandMetaFunction = p ->
                CommandMeta.simple()
                        // This will allow you to decorate commands with descriptions
                        .with(CommandMeta.DESCRIPTION, p.get(StandardParameters.DESCRIPTION, "No description"))
                        .build();
        AnnotationParser<CommandSender> annotationParser = new AnnotationParser<>(
                commandManager,
                CommandSender.class,
                commandMetaFunction
        );
        try {
            annotationParser.parseContainers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        annotationParser.parse(new TestModule());

        BukkitAudiences audiences = BukkitAudiences.create(this);
        new MinecraftExceptionHandler<CommandSender>()
                .withArgumentParsingHandler()
                .withInvalidSenderHandler()
                .withInvalidSyntaxHandler()
                .withNoPermissionHandler()
                .withCommandExecutionHandler()
                .withDecorator(message -> Component.text("T").append(Component.space()).append(message))
                .apply(commandManager, audiences::sender);

        help = new MinecraftHelp<CommandSender>(
                "/myplugin help",
                audiences::sender,
                commandManager
        );
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MinecraftHelp<CommandSender> getHelp() {
        return help;
    }
}

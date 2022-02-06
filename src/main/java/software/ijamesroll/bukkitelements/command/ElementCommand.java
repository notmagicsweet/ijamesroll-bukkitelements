package software.ijamesroll.bukkitelements.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ElementCommand {

    String name;
    String[] aliases;

    protected abstract void handleExecute(CommandSender sender, String[] args);
}
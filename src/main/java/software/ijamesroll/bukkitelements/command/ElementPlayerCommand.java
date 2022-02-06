package software.ijamesroll.bukkitelements.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ElementPlayerCommand {

    String name;
    String[] aliases;

    protected abstract void handleExecute(Player player, String[] args);
}

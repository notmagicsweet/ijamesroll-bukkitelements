package software.ijamesroll.bukkitelements.module;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.Listener;
import software.ijamesroll.bukkitelements.BukkitElements;
import software.ijamesroll.bukkitelements.module.annotation.ModuleInformation;

import java.util.ArrayList;
import java.util.Collection;

/**
 * © JamesRoll [ ijamesroll.software ] - JamesBukkitElements
 * This product is distributed under the MIT license
 * (https://github.com/ijamesroll/ijamesroll-bukkitelements/LICENSE)
 *
 * The author does not bear any responsibility for this development.
 * It is also prohibited to delete this copyright message.
 *
 * Author contacts: https://vk.com/ijamesroll, admin@ijamesroll.software, https://t.me/ijamesroll
 */
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ElementsModule {


    boolean enabled;
    ModuleInformation information;
    Collection<Listener> listeners = new ArrayList<>();

    public void handleEnable() {};
    public void handleDisable() {};

    public BukkitElements getBukkitElements(){
        return BukkitElements.getInstance();
    }

    public void registerListener(Listener listener) {
        ModuleManager.INSTANCE.registerListener(listener, this);
    }
}

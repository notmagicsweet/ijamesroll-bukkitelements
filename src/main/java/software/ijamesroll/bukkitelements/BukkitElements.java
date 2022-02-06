package software.ijamesroll.bukkitelements;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import software.ijamesroll.bukkitelements.module.ModuleManager;

import java.io.File;
import java.util.logging.Logger;

/**
 * © JamesRoll [ ijamesroll.software ] - JamesBukkitElements
 * This product is distributed under the MIT license
 * (https://github.com/jamesroll/ijamesroll-bukkitelements/LICENSE)
 *
 * The author does not bear any responsibility for this development.
 * It is also prohibited to delete this copyright message.
 *
 * Author contacts: https://vk.com/ijamesroll, admin@ijamesroll.software, https://t.me/ijamesroll
 */
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BukkitElements {

    ModuleManager moduleManager = ModuleManager.INSTANCE;
    // In ideas..
    File moduleFolder = new File("plugins/BukkitElements/module");
    Logger logger;

    @Getter
    private static BukkitElements instance; {
        instance = this;
    }

    public void reload() {

    }

    public void disable() {

    }
}

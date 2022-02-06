package software.ijamesroll.bukkitelements.module;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import software.ijamesroll.bukkitelements.BukkitElements;
import software.ijamesroll.bukkitelements.module.annotation.ModuleInformation;
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
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ElementsModule {

    @Setter
    private boolean enabled;
    @Setter
    @Getter
    private ModuleInformation information;

    public void handleEnable() {};
    public void handleDisable() {};

    public BukkitElements getBukkitElements(){
        return BukkitElements.getInstance();
    }
}

package net.runelite.client.plugins.xrunedragons.tasks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.DecorativeObject;
import net.runelite.api.MenuAction;
import net.runelite.api.events.GameTick;
import net.runelite.client.plugins.iutils.LegacyMenuEntry;
import net.runelite.client.plugins.xrunedragons.Task;
import net.runelite.client.plugins.xrunedragons.XRuneDragonsPlugin;

@Slf4j
public class TeleLithTask extends Task {

    @Override
    public boolean validate() {
        if (atPOH()) {
            if(!shouldRestock(false)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getTaskDescription() {
        return "Teleport to lith";
    }

    @Override
    public void onGameTick() {
        DecorativeObject decObstacle = object.findNearestDecorObject(33418);
        if (decObstacle != null) {
            started = true;
            entry = new LegacyMenuEntry("", "", decObstacle.getId(), MenuAction.GAME_OBJECT_FIRST_OPTION.getId(), decObstacle.getLocalLocation().getSceneX(), decObstacle.getLocalLocation().getSceneY(), false);
            utils.doActionMsTime(entry, decObstacle.getConvexHull().getBounds(), sleepDelay());
        }
    }

    @Override
    public void checkFinished() {
        if (atLith()) {
            XRuneDragonsPlugin.timeout = tickDelay();
            finished = true;
        }
    }
}
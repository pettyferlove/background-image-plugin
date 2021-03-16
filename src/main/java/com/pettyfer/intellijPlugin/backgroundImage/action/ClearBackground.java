package com.pettyfer.intellijPlugin.backgroundImage.action;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.pettyfer.intellijPlugin.backgroundImage.RandomBackgroundService;
import com.pettyfer.intellijPlugin.backgroundImage.ui.Settings;
import org.jetbrains.annotations.NotNull;

/**
 * @author Petty
 */
public class ClearBackground extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        prop.setValue(IdeBackgroundUtil.EDITOR_PROP, null);
        prop.setValue(IdeBackgroundUtil.FRAME_PROP, null);
        prop.setValue(IdeBackgroundUtil.TARGET_PROP, null);
        prop.setValue(Settings.AUTO_CHANGE, false);
        prop.setValue(Settings.IMAGES_FOLDER, null);
        RandomBackgroundService.stop();
    }
}

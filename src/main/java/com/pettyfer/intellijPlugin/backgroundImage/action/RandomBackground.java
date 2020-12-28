package com.pettyfer.intellijPlugin.backgroundImage.action;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.pettyfer.intellijPlugin.backgroundImage.BackgroundTask;
import com.pettyfer.intellijPlugin.backgroundImage.RandomBackgroundService;
import com.pettyfer.intellijPlugin.backgroundImage.ui.Settings;
import org.jetbrains.annotations.NotNull;

/**
 * @author Petty
 */
public class RandomBackground extends AnAction {

    public RandomBackground() {
        super("Random Background Image");
        new BackgroundTask().run();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PropertiesComponent props = PropertiesComponent.getInstance();
        if (props.getBoolean(Settings.AUTO_CHANGE, false)) {
            RandomBackgroundService.restart();
        } else {
            new BackgroundTask().run();
        }
    }
}

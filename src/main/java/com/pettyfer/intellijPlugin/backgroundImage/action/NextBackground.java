package com.pettyfer.intellijPlugin.backgroundImage.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.pettyfer.intellijPlugin.backgroundImage.BackgroundTask;
import org.jetbrains.annotations.NotNull;

/**
 * @author Petty
 */
public class NextBackground extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        new BackgroundTask().run();
    }
}

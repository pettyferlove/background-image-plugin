package com.pettyfer.intellijPlugin.backgroundImage;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.pettyfer.intellijPlugin.backgroundImage.ui.Settings;

import java.io.File;

/**
 * @author Petty
 */
public class BackgroundTask implements Runnable {

    @Override
    public void run() {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        String folder = prop.getValue(Settings.IMAGES_FOLDER);
        String area = prop.getValue(Settings.BACKGROUND_SET_AREA);
        String image = null;
        if (folder == null || folder.isEmpty()) {
            Notice.send("Image folder not set");
            return;
        }
        File file = new File(folder);
        if (!file.exists()) {
            Notice.send("Image folder not set");
            return;
        }
        image = ImagesSelectorSingleton.instance.getRandomImage(folder);
        if (image == null) {
            Notice.send("No image found");
            return;
        }
        if (image.contains(",")) {
            Notice.send("Intellij wont load images with ',' character\n" + image);
        }
        if(area == null){
            area = IdeBackgroundUtil.EDITOR_PROP + "," + IdeBackgroundUtil.FRAME_PROP + "," + IdeBackgroundUtil.TARGET_PROP;
        }
        String[] areas = area.split(",");
        for (String type : areas) {
            String lastImage = prop.getValue(type);
            if (lastImage != null && lastImage.contains(",")) {
                prop.setValue(type, image + lastImage.substring(lastImage.indexOf(",")));
            } else {
                prop.setValue(type, image);
            }
        }
    }

}

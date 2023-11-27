package pasa.cbentley.framework.coreui.fx.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Timer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import pasa.cbentley.framework.coreui.src4.interfaces.ICursors;

public class CursorFx {
   private int   currentIndex;

   private int   id;

   private Scene panel;

   private Timer timer;

   public CursorFx(Scene pan) {
      this.panel = pan;
   }

   public boolean updateCursor(Object s) {
      currentIndex = 0;

      if (timer != null) {
         timer.stop();
      }
      if (s instanceof Integer) {
         Integer in = (Integer) s;
         if (in.equals(ICursors.CURSOR_WAIT)) {
            panel.setCursor(Cursor.WAIT);
         }
      } else {
         if (s == null) {
            panel.setCursor(null);
            return true;
         } else {
            if (s instanceof String) {
               String str = (String) s;
               final List<ImageCursor> cursorsList = new ArrayList<ImageCursor>();
               try {
                  List<Image> frames = getFrames(str);
                  for (Image image : frames) {
                     ImageCursor ic = new ImageCursor(image);
                     cursorsList.add(ic);
                  }
                  if (cursorsList.size() > 1) {
                     timer = new Timer(50, new ActionListener() {
                        public void actionPerformed(ActionEvent actionEvent) {
                           panel.setCursor(cursorsList.get(currentIndex++));
                           if (currentIndex >= cursorsList.size())
                              currentIndex = 0;
                        }
                     });
                     timer.start();
                  } else {
                     panel.setCursor(cursorsList.get(0));
                  }
               } catch (IOException e) {
                  e.printStackTrace();
                  return false;
               }
               return true;
            }
         }
      }
      return false;
   }

   public List<Image> getFrames(String gif) throws IOException {
      List<Image> frames = new ArrayList<Image>();
      ImageInputStream stream = null;
      File input = new File(gif);
      if (input.exists()) {
         stream = ImageIO.createImageInputStream(input);
      } else {
         InputStream in = getClass().getResourceAsStream(gif);
         stream = ImageIO.createImageInputStream(in);
      }
      ImageReader reader = ImageIO.getImageReaders(stream).next();
      reader.setInput(stream);

      int count = reader.getNumImages(true);
      for (int index = 0; index < count; index++) {
         BufferedImage bi = reader.read(index);
         Image image = SwingFXUtils.toFXImage(bi, null);
         frames.add(image);
      }
      return frames;
   }

}

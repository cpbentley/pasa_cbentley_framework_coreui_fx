package pasa.cbentley.framework.core.ui.fx.wrapper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.draw.fx.engine.GraphicsFx;
import pasa.cbentley.framework.core.ui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.core.ui.fx.ctx.ITechStatorableCoreUiFx;
import pasa.cbentley.framework.core.ui.fx.engine.CanvasHostFx;
import pasa.cbentley.framework.core.ui.src4.interfaces.IWrapperManager;
import pasa.cbentley.framework.coredraw.src4.interfaces.IGraphics;

/**
 * Controls the Scene of JavaFX
 * @author Charles Bentley
 *
 */
public class WrapperFxTopStage extends WrapperAbstractFx {

   /**
    * The scene of the stage
    */
   private Scene scene;

   private Stage stage;

   public WrapperFxTopStage(CoreUiFxCtx cuc) {
      this(cuc, new Stage());
   }

   public WrapperFxTopStage(CoreUiFxCtx cuc, Stage stage) {
      super(cuc);

      //#debug
      toDLog().pFlow("stage=", this, WrapperFxTopStage.class, "WrapperFxTopStage", LVL_05_FINE, true);
      this.stage = stage;
   }

   public void addCanvasFx(CanvasHostFx canvas) {

      final Canvas canvasFx = canvas.getCanvasFx();
      Pane root = new Pane();
      root.getChildren().add(canvasFx);
      //create scene with root pane
      scene = new Scene(root, 100, 100);
      canvas.setScene(scene);
      stage.setScene(scene);
      canvas.setFrame(stage);
      setWindowListener(stage, canvas);
   }

   public int getStatorableClassID() {
      return ITechStatorableCoreUiFx.CLASSID_2_WRAPPER_FX_STAGE_FRAME;
   }

   public void canvasHide() {
      stage.hide();
   }

   public void canvasShow() {
      stage.show();
   }

   /**
    */
   public IGraphics getGraphics() {
      GraphicsContext myGraphics = null;
      IGraphics gx = new GraphicsFx(cuc.getCoreDrawFxCtx(), myGraphics);
      return gx;
   }

   /**
    * The JFrame.
    * <br>
    * When inside a SWT component, return null
    * @return null if no frame has been created yet
    */
   public Stage getStage() {
      return stage;
   }

   public int getX() {
      return (int) stage.getX();
   }

   public int getY() {
      return (int) stage.getY();
   }

   public boolean hasFeatureSupport(int feature) {
      if (feature == SUP_ID_26_CANVAS_RESIZE_MOVE) {
         return true;
      } else if (feature == SUP_ID_16_CUSTOM_CURSORS) {
         return true;
      } else if (feature == SUP_ID_28_ALWAYS_ON_TOP) {
         return true;
      } else if (feature == SUP_ID_29_UNDECORATED) {
         return true;
      }
      return false;
   }

   public boolean isContained() {
      return false;
   }

   public boolean isFeatureEnabled(int feature) {
      if (feature == SUP_ID_27_FULLSCREEN) {
         return stage.isFullScreen();
      } else if (feature == SUP_ID_28_ALWAYS_ON_TOP) {
         return stage.isAlwaysOnTop();
      } else if (feature == SUP_ID_29_UNDECORATED) {
         return stage.getStyle() == StageStyle.UNDECORATED;
      } else if (feature == SUP_ID_30_MINIMIZE) {
         //difference with maximized?
         return stage.isIconified();
      } else if (feature == SUP_ID_31_ACTIVATE_FRONT) {
      }
      return false;
   }

   public boolean setAlwaysOnTop(boolean mode) {
      stage.setAlwaysOnTop(mode);
      return true;
   }

   public void setDefaultStartPosition() {
      int frameWidth = 800;
      int frameHeight = 400;
      int frameX = 20;
      int frameY = 20;
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      if (frameX < 0 || frameX > screenSize.width) {
         frameX = screenSize.width / 2 - frameWidth / 2;
      }
      if (frameY < 0 || frameY > screenSize.height) {
         frameY = screenSize.height / 2 - frameHeight / 2;
      }
      stage.setX(frameX);
      stage.setY(frameY);
      stage.setHeight(frameHeight);
      stage.setWidth(frameWidth);
   }

   public boolean setFeature(int feature, boolean mode) {
      if (feature == SUP_ID_27_FULLSCREEN) {
         setFullScreenMode(mode);
         return true;
      } else if (feature == SUP_ID_28_ALWAYS_ON_TOP) {
         stage.setAlwaysOnTop(mode);
         return true;
      } else if (feature == SUP_ID_29_UNDECORATED) {
         return setUndecorated(mode);
      } else if (feature == SUP_ID_30_MINIMIZE) {
         if (mode) {
            stage.setIconified(true);
         } else {
            stage.setIconified(false);
         }
         return true;
      } else if (feature == SUP_ID_31_ACTIVATE_FRONT) {
      }
      return false;
   }

   /**
    * How to set full screen mode in Swing?
    * That's the job of the {@link DeviceDriver}. Similarly for vibration and light uses.
    * <br>
    * When Canvas is the sole owner of the JFrame.
    * <br>
    * In the case of a Mosaic, the fullscreen call does nothing
    * @param mode
    */
   public void setFullScreenMode(boolean mode) {
      stage.setFullScreen(mode);
   }

   public void setIcon(String path) {
      if (path != null) {
         try {
            //#debug
            String msg = " loading Icon " + path;
            //#debug
            toDLog().pBridge(msg, this, WrapperFxTopStage.class, "setIcon", LVL_05_FINE, true);
            InputStream is = this.getClass().getResourceAsStream(path);
            if (is != null) {
               stage.getIcons().add(new Image(is));
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   public void setPosition(int x, int y) {
      //#debug
      toDLog().pBridge("x=" + x + ", y=" + y, this, WrapperFxTopStage.class, "setPosition");
      stage.setX(x);
      stage.setY(y);
   }

   public void setSize(int w, int h) {
      stage.setWidth(w);
      stage.setHeight(h);

      //#debug
      toDLog().pBridge("w=" + w + ", h=" + h + " ActuFrame=" + (int) stage.getWidth() + "," + (int) stage.getHeight(), this, WrapperFxTopStage.class, "setSize");
   }

   public void setTitle(String str) {
      stage.setTitle(str);
   }

   /**
    * Cannot change the style in JavaFX
    */
   public boolean setUndecorated(boolean mode) {
      //disable implicit exit when no stage is visible
      Platform.setImplicitExit(false);

      StageStyle ss = stage.getStyle();
      if (ss == StageStyle.DECORATED) {

      }
      if (mode) {
      }
      return true;
   }

   protected void setWindowListener(Stage stage, final CanvasHostFx canvas) {
      JavaFxIconifiedChangeListener iconified = new JavaFxIconifiedChangeListener(cuc, canvas);

      stage.iconifiedProperty().addListener(iconified);

      JavaFxFocusChangeListener focus = new JavaFxFocusChangeListener(cuc, canvas);
      stage.focusedProperty().addListener(focus);

      JavaFxEventHandlerClose close = new JavaFxEventHandlerClose(cuc, canvas);
      JavaFxEventHandlerHide hide = new JavaFxEventHandlerHide(cuc, canvas);

      stage.setOnCloseRequest(close);
      stage.setOnHiding(hide);

   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, WrapperFxTopStage.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());

      if (stage == null) {
         dc.append("stage is null");
      } else {
         //
         dc.appendVarWithSpace("isPrimaryStage", cuc.getStagePrimary() == stage);
         dc.appendVarWithSpace("Title", stage.getTitle());
         dc.nl();
         dc.appendVarWithSpace("Position", stage.getX() + "," + stage.getY());
         dc.appendVarWithSpace("Size", stage.getWidth() + "," + stage.getHeight());
         dc.nl();
         dc.appendVarWithSpace("isAlwaysOnTop", stage.isAlwaysOnTop());
      }
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, WrapperFxTopStage.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}

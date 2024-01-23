package pasa.cbentley.framework.coreui.fx.engine;

import java.awt.AWTException;
import java.awt.Robot;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.src4.app.IAppli;
import pasa.cbentley.framework.coredraw.fx.engine.GraphicsFx;
import pasa.cbentley.framework.coredraw.src4.interfaces.IGraphics;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.coreui.fx.wrapper.WrapperAbstractFx;
import pasa.cbentley.framework.coreui.j2se.engine.CanvasHostJ2SE;
import pasa.cbentley.framework.coreui.src4.ctx.ToStringStaticCoreUi;
import pasa.cbentley.framework.coreui.src4.tech.ITechCodes;

/**
 * JavaFX is uses a retained mode rendering model whereas Swing uses an immediate mode.
 * @author Charles-Philip
 *
 */
public abstract class CanvasHostFx extends CanvasHostJ2SE {

   private int[]               active           = new int[15];

   /**
    * 
    */
   protected Canvas            canvasFx;

   protected final CoreUiFxCtx cuc;

   private GraphicsFx          graphics;

   private boolean             isLastMouseMouse = false;

   /**
    * When true, there is at least 1 touch event after the last recorded mouse event.
    */
   private boolean             isLastMouseTouch = false;

   long                        lastFinishedScrollingTime;

   private int                 lastMouseX;

   private int                 lastMouseXRel;

   private int                 lastMouseY;

   private int                 lastMouseYRel;

   private int                 lastTouch0X;

   private int                 lastTouch0Y;

   /**
    * 
    */
   private Scene               scene;

   protected Stage             stage;

   boolean                     trackpadScrolling;

   /**
    * 
    * @param c when null, the Canvas will be set externallyO
    * @param w
    * @param h
    */
   public CanvasHostFx(CoreUiFxCtx cuc, ByteObject tech) {
      super(cuc, tech);
      this.cuc = cuc;

      //      LaunchValues lv = dd.getLaunchValues();
      //      int iw = lv.w;
      //      int ih = lv.h;
      //      //#debug
      //      toDLog().pBridge("Image buffer size = " + iw + "," + ih, null, CanvasAbstractFx.class, "constructor");
      //      
      canvasFx = new RealCanvasFx(cuc);

      canvasFx.focusedProperty().addListener(new ChangeListener<Boolean>() {

         public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            focusState(newValue);
         }
      });
      canvasFx.setOnMousePressed(new EventHandler<MouseEvent>() {
         public void handle(MouseEvent event) {
            mousePressed(event);
         }
      });
      canvasFx.setOnMouseReleased(new EventHandler<MouseEvent>() {
         public void handle(MouseEvent event) {
            mouseReleased(event);
         }
      });

      canvasFx.setOnScrollStarted(new EventHandler<ScrollEvent>() {
         public void handle(ScrollEvent event) {
            trackpadScrolling = true;
         }
      });

      canvasFx.setOnScrollFinished(new EventHandler<ScrollEvent>() {
         public void handle(ScrollEvent event) {
            trackpadScrolling = false;
            lastFinishedScrollingTime = System.currentTimeMillis();
         }
      });
      canvasFx.setOnScroll(new EventHandler<ScrollEvent>() {
         public void handle(ScrollEvent ev) {
            long timeDiff = System.currentTimeMillis() - lastFinishedScrollingTime;
            boolean ghostEvent = timeDiff < 1000; // I saw 500-700ms ghost events
            if (trackpadScrolling || ev.isInertia() || ghostEvent) {
               // trackpad scrolling
            } else {
               // mouse scrolling
               mouseWheelMoved(ev);
            }
         }
      });

      canvasFx.setOnMouseEntered(new EventHandler<MouseEvent>() {
         public void handle(MouseEvent ev) {
            mouseEntered(ev);
         }
      });
      canvasFx.setOnMouseExited(new EventHandler<MouseEvent>() {
         public void handle(MouseEvent ev) {
            mouseExited(ev);
         }
      });
      canvasFx.setOnMouseMoved(new EventHandler<MouseEvent>() {
         public void handle(MouseEvent ev) {
            mouseMoved(ev);
         }
      });
      canvasFx.setOnMouseDragged(new EventHandler<MouseEvent>() {
         public void handle(MouseEvent ev) {
            mouseMoved(ev);
         }
      });

      canvasFx.setOnTouchPressed(new EventHandler<TouchEvent>() {
         public void handle(TouchEvent event) {
            fingerPressed(event);
            event.consume();
         }
      });

      canvasFx.setOnTouchReleased(new EventHandler<TouchEvent>() {
         public void handle(TouchEvent event) {
            fingerReleased(event);
            event.consume();
         }
      });

      canvasFx.setOnTouchMoved(new EventHandler<TouchEvent>() {
         public void handle(TouchEvent event) {
            fingerMoved(event);
            event.consume();
         }
      });

      graphics = new GraphicsFx(cuc.getCoreDrawFxCtx(), canvasFx.getGraphicsContext2D());
   }

   public void componentResized(int w, int h) {
      //we already are in the AWT Event Queue.

      canvasFx.setWidth(w);
      canvasFx.setHeight(h);
      //TODO be careful with the clip
      canvasFx.setClip(null);

      //#debug
      toDLog().pBridge("[" + w + ":" + h + "]", this, CanvasHostFx.class, "componentResized", LVL_05_FINE, true);

      //send a resize event from the framework

      if (w <= 0) {
         //#debug
         toDLog().pBridge("w<=0" + w, this, CanvasHostFx.class, "componentResized", LVL_05_FINE, true);
         w = 1;
      }
      if (h <= 0) {
         //#debug
         toDLog().pBridge("h<=0" + h, this, CanvasHostFx.class, "componentResized", LVL_05_FINE, true);
         h = 1;
      }
      super.canvasSizeChangedBridge(w, h);
      paint();
   }

   private void debugKeyEvent(KeyEvent ev, String str, int bentleyCode) {
      String loc = "";
      String modss = "";
      if (ev.isShiftDown()) {
         modss += "Shift";
      }
      if (ev.isControlDown()) {
         modss += "Ctrl";
      }
      if (ev.isAltDown()) {
         modss += "Alt";
      }
      if (ev.isMetaDown()) {
         modss += "AltGr";
      }
      int j2seKeyCode = ev.getCode().impl_getCode();
      char c = ev.getCharacter().charAt(0);
      //#debug
      String msg = str + " KeyChar=" + c + " KeyCode=" + j2seKeyCode + " translated to [" + bentleyCode + "] " + ToStringStaticCoreUi.getStringKey(bentleyCode) + " " + modss + " " + loc;
      //#debug
      toDLog().pBridge(msg, this, CanvasHostFx.class, "debugKeyEvent", LVL_05_FINE, true);
   }

   /**
    * 
    * @param event
    */
   public void fingerMoved(TouchEvent event) {
      int touchId = event.getTouchPoint().getId();
      int fingerID = 0;
      for (int i = 1; i < active.length; i++) {
         if (active[i] == touchId) {
            fingerID = i;
            break;
         }
      }
      double touchx = event.getTouchPoint().getSceneX();
      double touchy = event.getTouchPoint().getSceneY();
      //#debug
      toDLog().pBridge("[" + (int) touchx + " " + (int) touchy + "]" + " fingerID=" + fingerID, null, CanvasHostFx.class, "fingerMoved");
      fingerMovedBridge((int) touchx, (int) touchy, fingerID, fingerID);
      isLastMouseTouch = true;
      lastTouch0X = (int) touchx;
      lastTouch0Y = (int) touchy;
      moveCursor(lastMouseX, lastMouseY);
   }

   public void fingerPressed(TouchEvent event) {
      //TODO when 2 or more touchscreen. do we identify? case of big canvas over 2 screen
      int touchId = event.getTouchPoint().getId();
      int fingerID = 0;
      for (int i = 1; i < active.length; i++) {
         if (active[i] == 0) {
            active[i] = touchId;
            fingerID = i;
            break;
         }
      }
      double touchx = event.getTouchPoint().getSceneX();
      double touchy = event.getTouchPoint().getSceneY();
      //#debug
      toDLog().pBridge("[" + (int) touchx + " " + (int) touchy + "]" + " fingerID=" + fingerID, null, CanvasHostFx.class, "fingerPressed");
      //pointerID depends internal setup. once a device finger has been associated a pointerID
      // it will keep it. pointerID is internal.
      fingerPressedBridge((int) touchx, (int) touchy, fingerID, fingerID);
      isLastMouseTouch = true;
   }

   public void fingerReleased(TouchEvent event) {
      int touchId = event.getTouchPoint().getId();
      int fingerID = 0;
      for (int i = 1; i < active.length; i++) {
         if (active[i] == touchId) {
            active[i] = 0;
            fingerID = i;
            break;
         }
      }
      double touchx = event.getTouchPoint().getSceneX();
      double touchy = event.getTouchPoint().getSceneY();
      //#debug
      toDLog().pBridge("[" + (int) touchx + " " + (int) touchy + "]" + " fingerID=" + fingerID, null, CanvasHostFx.class, "fingerReleased");
      fingerReleasedBridge((int) touchx, (int) touchy, fingerID, fingerID);
      isLastMouseTouch = true;
      moveCursor(lastMouseX, lastMouseY);
   }

   public void flushGraphics() {
      // TODO Auto-generated method stub

   }

   private void focusState(boolean value) {
      if (value) {
         focusGainedBridge();
      } else {
         focusLostBridge();
      }
   }

   /**
    * Maps the {@link MouseEvent} to the Bentley's framework {@link ITechCodes} IDs.
    * <br>
    * <br>
    * 
    * @param e
    * @return
    */
   protected int getButtonID(MouseEvent e) {
      int butID = ITechCodes.PBUTTON_0_DEFAULT;
      MouseButton mb = e.getButton();
      if (mb == MouseButton.SECONDARY) {
         butID = ITechCodes.PBUTTON_1_RIGHT;
      } else if (mb == MouseButton.MIDDLE) {
         butID = ITechCodes.PBUTTON_2_MIDDLE;
      }
      return butID;
   }

   public Canvas getCanvasFx() {
      return canvasFx;
   }

   public IGraphics getGraphics() {
      throw new RuntimeException();
   }

   public int getICHeight() {
      return (int) canvasFx.getHeight();
   }

   public int getICWidth() {
      return (int) canvasFx.getWidth();
   }

   public Scene getScene() {
      return canvasFx.getScene();
   }

   public Stage getStage() {
      return stage;
   }

   public WrapperAbstractFx getWrapperFX() {
      return (WrapperAbstractFx) wrapperJ2SE;
   }

   /**
    * Might be called outside the FX thread
    */
   public void icRepaint() {
      paint();

   }

   public void icRepaint(int x, int y, int w, int h) {
      icRepaint();
   }

   public void icServiceRepaint() {
      if (!Platform.isFxApplicationThread()) {
         //toLog().printBridge("#JavaFxCanvasBridge#paint Inside FX Thread");
      }
   }

   /**
    * Return false when synthesized.
    *  and when just after a Touch.
    *  
    * It is not a real mouse mouve, when it is directly around the last Touch Move
    * @param ev
    * @return
    */
   private boolean isEventRealMouse(MouseEvent ev) {
      if (!ev.isSynthesized()) {

         if ((int) ev.getX() == lastTouch0X) {
            if ((int) ev.getY() == lastTouch0Y) {
               return false;
            }
         }
         if (isLastMouseTouch) {
            //lastMouseWasTouch();
            int screenX = lastMouseX;
            int screenY = lastMouseY;
            //we have to make sure the mouse is not moved already. there is a lag
            //moveCursor(screenX, screenY);
            isLastMouseTouch = false;
            if ((int) ev.getScreenX() == lastMouseX) {
               if ((int) ev.getScreenY() == lastMouseY) {
                  //mouse event is due to a robot move
                  return false;
               }
            }
            return true;
         } else {
            //we are between consecutive mouse events. simply records
            lastMouseX = (int) ev.getScreenX();
            lastMouseY = (int) ev.getScreenY();
            lastMouseXRel = (int) ev.getSceneX();
            lastMouseYRel = (int) ev.getSceneY();
            return true;
         }
      }
      return false;
   }

   public boolean isShown() {
      // TODO Auto-generated method stub
      return false;
   }

   /**
    * Method from the KeyListener interface.
    * <br>
    * <b>Notes</b>:
    * <li>Also, as long as the key is pressed, it generates an event 
    * <li>Swing generates a new event when A press B press A released, Swing generated a Pressed event.
    * <br>
    * <br>
    * 
    */
   public void keyPressed(KeyEvent ev) {
      int bentleyCode = cuc.getKeyMapFx().getKeyMappedToFramework(ev.getCode());
      if (cuc.isKeyRepeat(bentleyCode)) {
         return;
      }
      simulationKeys(bentleyCode);
      debugKeyEvent(ev, "Pressed", bentleyCode);
      keyPressedBridge(bentleyCode);
   }

   /**
    * Method from the KeyListener interface.
    */
   public void keyReleased(KeyEvent ev) {
      int bentleyCode = cuc.getKeyMapFx().getKeyMappedToFramework(ev.getCode());
      cuc.keyReleaseRepeat(bentleyCode);
      debugKeyEvent(ev, "Released", bentleyCode);
      keyReleasedBridge(bentleyCode);
   }

   /**
    * Move mouse cursor to its last recorded position
    */
   private void lastMouseWasTouch() {
      int screenX = lastMouseX;
      int screenY = lastMouseY;
      moveCursor(screenX, screenY);
      isLastMouseTouch = false;
   }

   public void mouseDragged(MouseEvent ev) {
      //#debug
      toDLog().pBridge("[" + ev.getX() + " " + ev.getY() + "]", null, CanvasHostFx.class, "mouseDragged");
      mouseMoved(ev);
      //mouseDraggedBridge((int) ev.getX(), (int) ev.getY(), 0);
   }

   /**
    * When the mouse enters, it sends a release event if the mouse is not being dragged.
    * <br>
    * This class must track the state of mouse buttons and send release events when those are released
    * outside
    */
   public void mouseEntered(MouseEvent e) {
      //#debug
      toDLog().pBridge("[" + e.getX() + " " + e.getY() + "]", this, CanvasHostFx.class, "mouseEntered", LVL_05_FINE, true);
      //this.requestFocusInWindow();
      mouseEnteredBridge((int) e.getX(), (int) e.getY());
   }

   /**
    * When the mouse exits the {@link SwingCanvasBridge}, it might be in the pressed state.
    * <br>
    * There is no certainty that the released event outside the {@link SwingCanvasBridge} will be forwarded here.
    * <br>
    * Therefore we need a flag when the {@link SwingCanvasBridge#mouseEntered(MouseEvent)}
    * <br>
    * 
    */
   public void mouseExited(MouseEvent ev) {
      //#debug
      toDLog().pBridge("[" + ev.getX() + " " + ev.getY() + "]", null, CanvasHostFx.class, "mouseExited");
      mouseExitedBridge((int) ev.getX(), (int) ev.getY());
   }

   /**
    * The issue is that when the system tracks the system mouse to the moves.. so the first non synthesized event after a touch event
    * must move the mouse to prevent mouse location
    * @param ev
    */
   public void mouseMoved(MouseEvent ev) {
      boolean v = isEventRealMouse(ev);
      //TODO fork the code when there is at least one finger down.
      // in this case, when true mouse is moving. it must be relative
      // to the last free mouse point
      //#debug
      toDLog().pBridge("[" + (int) ev.getX() + " " + (int) ev.getY() + "] isSynthesized=" + ev.isSynthesized() + " Last=" + lastMouseXRel + "," + lastMouseYRel + " isLastMouseTouch=" + isLastMouseTouch + " isReal=" + v, null, CanvasHostFx.class, "mouseMoved");
      if (v) {
         mouseMovedBridge((int) ev.getX(), (int) ev.getY(), 0);
      }
   }

   public void mousePressed(MouseEvent ev) {
      int bid = getButtonID(ev);
      //#debug
      toDLog().pBridge("[" + (int) ev.getX() + " " + (int) ev.getY() + "]" + " butID=" + bid + " isSynthesized=" + ev.isSynthesized(), null, CanvasHostFx.class, "mousePressed");
      if (isEventRealMouse(ev)) {
         //moveCursor(lastMouseX, lastMouseY);
         mousePressedBridge((int) ev.getX(), (int) ev.getY(), 0, bid);
      }
   }

   public void mouseReleased(MouseEvent ev) {
      int bid = getButtonID(ev);
      //#debug
      toDLog().pBridge("[" + (int) ev.getX() + " " + (int) ev.getY() + "]" + " butID=" + bid + " isSynthesized=" + ev.isSynthesized(), null, CanvasHostFx.class, "mouseReleased");
      if (isEventRealMouse(ev)) {
         //when
         mouseReleasedBridge((int) ev.getX(), (int) ev.getY(), 0, bid);
      }
   }

   public void mouseWheelMoved(ScrollEvent e) {
      int amount = (int) e.getDeltaY();
      int rot = 1;
      if (amount > 0) {
         rot = -1;
      } else {
         rot = 1;
         amount = -amount;
      }
      //#debug
      String msg = String.format("deltaX: %.3f deltaY: %.3f", e.getDeltaX(), e.getDeltaY()) + " am=" + amount + " rot=" + rot;
      //#debug
      toDLog().pBridge(msg, this, CanvasHostFx.class, "mouseWheelMoved", LVL_05_FINE, true);

      mouseWheeledBridge(amount, rot);
   }

   /**
    * Move the mouse to the specific screen position
    * TODO don't move if secondary finger moved
    * @param x
    * @param y
    */
   public void moveCursor(final int screenX, final int screenY) {
      Platform.runLater(new Runnable() {
         public void run() {
            try {
               Robot robot = new Robot();
               robot.mouseMove(screenX, screenY);
            } catch (AWTException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      });
   }

   public void onAppModuleChange(IAppli app) {

   }

   public void paint() {
      if (Platform.isFxApplicationThread()) {
         //toLog().printBridge("#JavaFxCanvasBridge#paint Inside FX Thread");
         paintBridge(graphics);
      } else {
         //toLog().printBridge("#JavaFxCanvasBridge#paint Outside FX Thread");
         Platform.runLater(new Runnable() {
            public void run() {
               paintBridge(graphics);
            }
         });
      }

   }

   public boolean setCanvasFeature(int feature, boolean mode) {
      return false;
   }

   /**
    * Set by the Wrapper
    * @param frame
    */
   public void setFrame(Stage frame) {
      this.stage = frame;
   }

   public void setScene(Scene scene) {
      this.scene = scene;
      //javafx quirks.. need to set on scene not canvas
      scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
         public void handle(KeyEvent ev) {
            keyPressed(ev);
         }
      });
      scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
         public void handle(KeyEvent ev) {
            keyReleased(ev);
         }
      });
      scene.widthProperty().addListener(new ChangeListener<Number>() {
         public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
            componentResized(newSceneWidth.intValue(), (int) canvasFx.getHeight());

         }
      });
      scene.heightProperty().addListener(new ChangeListener<Number>() {
         public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
            componentResized((int) canvasFx.getWidth(), (int) newSceneHeight.intValue());
         }
      });
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, CanvasHostFx.class, "@line599");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, CanvasHostFx.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}

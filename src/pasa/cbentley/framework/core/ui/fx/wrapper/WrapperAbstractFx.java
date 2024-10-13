package pasa.cbentley.framework.core.ui.fx.wrapper;

import pasa.cbentley.framework.core.ui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.core.ui.fx.engine.CanvasHostFx;
import pasa.cbentley.framework.core.ui.j2se.engine.CanvasHostJ2se;
import pasa.cbentley.framework.core.ui.j2se.engine.WrapperAbstractJ2se;
import pasa.cbentley.framework.core.ui.src4.engine.CanvasHostAbstract;
import pasa.cbentley.framework.core.ui.src4.interfaces.IWrapperManager;
import pasa.cbentley.framework.coredraw.src4.interfaces.IGraphics;

public abstract class WrapperAbstractFx extends WrapperAbstractJ2se {

   protected CanvasHostFx canvas;

   protected final CoreUiFxCtx cuc;

   protected WrapperAbstractFx(CoreUiFxCtx cuc) {
      super(cuc);
      this.cuc = cuc;
   }

   public abstract void addCanvasFx(CanvasHostFx canvas);

   public void canvasHide() {

   }

   public void canvasShow() {

   }

   public CanvasHostJ2se getCanvas() {
      return canvas;
   }

   public IGraphics getGraphics() {
      return null;
   }

   public int getX() {
      return 0;
   }

   public int getY() {
      return 0;
   }

   public boolean isFeatureEnabled(int feature) {
      return false;
   }

   public boolean setAlwaysOnTop(boolean mode) {
      return false;
   }

   public void setCanvasHost(CanvasHostAbstract can) {
      if (can instanceof CanvasHostFx) {
         this.canvas = (CanvasHostFx) can;
         addCanvasFx(canvas);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void setFullScreenMode(boolean mode) {

   }

   /** Initialize the wrapper with the Canvas.
    * <br><br>
    * Defines the kernel and everything.
    * 
    * Q:Is the size of the Canvas already decided?
    * A:
    * <br>
    * @param can
    */
   public void setFXCanvas(CanvasHostFx can) {
      this.canvas = can;
   }

   public void setIcon(String str) {

   }

   public void setPosition(int x, int y) {

   }

   public void setSize(int w, int h) {

   }

   public void setTitle(String str) {

   }

   public boolean setUndecorated(boolean mode) {
      return false;
   }

}

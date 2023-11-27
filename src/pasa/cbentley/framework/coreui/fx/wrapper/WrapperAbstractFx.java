package pasa.cbentley.framework.coreui.fx.wrapper;

import pasa.cbentley.framework.coredraw.src4.interfaces.IGraphics;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.coreui.fx.engine.CanvasHostFx;
import pasa.cbentley.framework.coreui.j2se.engine.CanvasHostJ2SE;
import pasa.cbentley.framework.coreui.j2se.engine.WrapperAbstractJ2SE;
import pasa.cbentley.framework.coreui.src4.engine.CanvasHostAbstract;

public abstract class WrapperAbstractFx extends WrapperAbstractJ2SE {

   protected final CoreUiFxCtx cuc;

   protected WrapperAbstractFx(CoreUiFxCtx cuc) {
      super(cuc);
      this.cuc = cuc;
   }

   protected CanvasHostFx canvas;

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

   public void setCanvasHost(CanvasHostAbstract can) {
      if (can instanceof CanvasHostFx) {
         this.canvas = (CanvasHostFx) can;
         addCanvasFx(canvas);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public abstract void addCanvasFx(CanvasHostFx canvas);

   public void canvasShow() {

   }

   public CanvasHostJ2SE getCanvas() {
      return canvas;
   }

   public void canvasHide() {

   }

   public void setIcon(String str) {

   }

   public void setTitle(String str) {

   }

   public void setFullScreenMode(boolean mode) {

   }

   public void setSize(int w, int h) {

   }

   public int getY() {
      return 0;
   }

   public int getX() {
      return 0;
   }

   public void setPosition(int x, int y) {

   }

   public boolean setUndecorated(boolean mode) {
      return false;
   }

   public boolean setAlwaysOnTop(boolean mode) {
      return false;
   }

   public boolean isFeatureEnabled(int feature) {
      return false;
   }

   public IGraphics getGraphics() {
      return null;
   }

}

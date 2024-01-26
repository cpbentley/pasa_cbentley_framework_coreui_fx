package pasa.cbentley.framework.coreui.fx.wrapper;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.coreui.src4.engine.WrapperAbstract;
import pasa.cbentley.framework.coreui.src4.interfaces.IWrapperManager;
import pasa.cbentley.framework.coreui.src4.tech.IBOCanvasHost;

/**
 * Creates 
 * @author Charles Bentley
 *
 */
public class CanvasOwnerDefaultFx implements IWrapperManager {

   protected final CoreUiFxCtx cuic;

   public CanvasOwnerDefaultFx(CoreUiFxCtx cuic) {
      this.cuic = cuic;
   }

   /**
    * Wrapper depends on Launcher. 
    * <br>
    * For in game Canvas, 
    * For the root Canvas of the application.
    * Wrapper Manager
    * @param tech
    * @return
    */
   public WrapperAbstractFx getWrapper(ByteObject tech) {
      WrapperAbstractFx scw = null;
      //interpret this depending on root manager
      int wrapperType = tech.get1(IBOCanvasHost.TCANVAS_OFFSET_02_WRAPPER_TYPE1);
      if (wrapperType == IBOCanvasHost.TCANVAS_TYPE_0_DEFAULT) {
         //in a controlled env.. the wrapper is a panel and all new windows must be inside the 
         //semi multi. a new component is drawn over the old one.. like in andoird. screen size is fixed.
         scw = new WrapperFxTopStage(cuic);
      } else if (wrapperType == IBOCanvasHost.TCANVAS_TYPE_1_FRAME) {
         scw = new WrapperFxTopStage(cuic);
      } else if (wrapperType == IBOCanvasHost.TCANVAS_TYPE_2_CONTROLLED) {
         scw = new WrapperFxTopStage(cuic);
      }
      return scw;
   }

   public void setIcon(WrapperAbstractFx wrapper, String str) {

   }

   public void setTitle(String str) {

   }

   public void setFullScreenMode(boolean mode) {

   }

   public void setSize(int w, int h) {

   }

   public void setPosition(int x, int y) {

   }

   public boolean setUndecorated(boolean mode) {
      return false;
   }

   public boolean setAlwaysOnTop(boolean mode) {
      return false;
   }

   public String toString1Line() {
      // TODO Auto-generated method stub
      return null;
   }

   public void toString(Dctx dc) {
      // TODO Auto-generated method stub
      
   }

   public void toString1Line(Dctx dc) {
      // TODO Auto-generated method stub
      
   }

   public UCtx toStringGetUCtx() {
      // TODO Auto-generated method stub
      return null;
   }

   public void setTitle(WrapperAbstract wrapper, String title) {
      // TODO Auto-generated method stub
      
   }

   public boolean setSize(WrapperAbstract wrapper, int w, int h) {
      // TODO Auto-generated method stub
      return false;
   }

   public boolean setPosition(WrapperAbstract wrapper, int x, int y) {
      // TODO Auto-generated method stub
      return false;
   }

   public WrapperAbstract createNewWrapper(ByteObject tech) {
      // TODO Auto-generated method stub
      return null;
   }

}

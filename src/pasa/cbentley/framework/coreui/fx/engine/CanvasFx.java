package pasa.cbentley.framework.coreui.fx.engine;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.src4.interfaces.ITechHostCore;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.coreui.src4.interfaces.ICanvasHost;
import pasa.cbentley.framework.coreui.src4.tech.ITechHostUI;

public class CanvasFx extends CanvasHostFx implements ICanvasHost {

   public CanvasFx(CoreUiFxCtx dd, ByteObject tech) {
      super(dd, tech);
 
   }

   public boolean isCanvasFeatureSupported(int feature) {
      if (feature == ITechHostCore.SUP_ID_26_CANVAS_RESIZE_MOVE) {
         //check if owner of frame
      } else if (feature == ITechHostCore.SUP_ID_16_CUSTOM_CURSORS) {
         return true;
      }
      return super.isCanvasFeatureSupported(feature);
   }

   public void icSetSize(int w, int h) {
      wrapper.setSize(w, h);
      canvasFx.setWidth(w);
      canvasFx.setHeight(h);
   }

   public boolean setCanvasFeature(int feature, Object mode) {
      if (feature == ITechHostUI.SUP_ID_16_CUSTOM_CURSORS) {
         if (mode == null || mode instanceof String) {
            CursorFx cs = new CursorFx(getScene());
            boolean b = cs.updateCursor(mode);
            return b;
         }
         return false;
      }
      return false;
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, CanvasFx.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, CanvasFx.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   


}

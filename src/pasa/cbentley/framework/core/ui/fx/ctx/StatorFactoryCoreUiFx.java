package pasa.cbentley.framework.core.ui.fx.ctx;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.stator.StatorReaderBO;
import pasa.cbentley.core.src4.ctx.ICtx;
import pasa.cbentley.core.src4.stator.IStatorFactory;
import pasa.cbentley.core.src4.stator.IStatorable;
import pasa.cbentley.core.src4.stator.ITechStator;
import pasa.cbentley.core.src4.stator.StatorReader;
import pasa.cbentley.framework.core.ui.fx.engine.CanvasFx;
import pasa.cbentley.framework.core.ui.fx.wrapper.WrapperFxTopStage;

public class StatorFactoryCoreUiFx implements IStatorFactory, ITechStatorableCoreUiFx {

   private CoreUiFxCtx cuc;

   public StatorFactoryCoreUiFx(CoreUiFxCtx cuc) {
      this.cuc = cuc;
   }

   public Object[] createArray(int classID, int size) {
      switch (classID) {
         case CLASSID_1_CANVAS_FX:
            return new CanvasFx[size];
         case CLASSID_2_WRAPPER_FX_STAGE_FRAME:
            return new WrapperFxTopStage[size];

         default:
            break;
      }
      return null;
   }

   private Object createCanvasFx(StatorReader reader) {
      StatorReaderBO srbo = (StatorReaderBO) reader;
      srbo.checkInt(ITechStator.MAGIC_WORD_OBJECT_PARAM);
      ByteObject boCanvasHost = srbo.readByteObject();
      return new CanvasFx(cuc, boCanvasHost);
   }

   public Object createObject(StatorReader reader, int classID) {
      switch (classID) {
         case CLASSID_1_CANVAS_FX:
            return createCanvasFx(reader);
         case CLASSID_2_WRAPPER_FX_STAGE_FRAME:
            return new WrapperFxTopStage(cuc);

         default:
            break;
      }
      return null;
   }

   public ICtx getCtx() {
      return cuc;
   }

   public boolean isSupported(IStatorable statorable) {
      if (statorable.getClass() == CanvasFx.class) {
         return true;
      } else if (statorable.getClass() == WrapperFxTopStage.class) {
         return true;
      }
      return false;
   }

}

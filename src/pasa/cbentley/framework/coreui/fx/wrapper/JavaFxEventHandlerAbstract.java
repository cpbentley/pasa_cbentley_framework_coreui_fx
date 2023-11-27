package pasa.cbentley.framework.coreui.fx.wrapper;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.coreui.fx.engine.CanvasHostFx;

public abstract class JavaFxEventHandlerAbstract implements IStringable {

   protected final CoreUiFxCtx      cuc;

   protected final CanvasHostFx canvas;

   public JavaFxEventHandlerAbstract(CoreUiFxCtx cuc, CanvasHostFx canvas) {
      this.cuc = cuc;
      this.canvas = canvas;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "JavaFxEventHandlerAbstract");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "JavaFxEventHandlerAbstract");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return cuc.getUCtx();
   }
   //#enddebug

}
